package database.implementation;

import database.DBAccess;
import database.interfaces.IBudgetService;
import database.interfaces.ICurrencyService;
import models.Budget;
import models.Expense;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetService implements IBudgetService {

    private ICurrencyService currencyService;

    public BudgetService(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public boolean add(Budget budget) {
        System.out.println("Trying to add "+budget);
        if(!budgetNotExists(budget.getCategoryId(),budget.getMonth(),budget.getYear(),budget.getOwnerId())) return false;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("insert into budgets(amount,alreadySpent,month,year,categoryId,currencyId,ownerId) values (?,?,?,?,?,?,?)"))
        {
            preparedStatement.setInt(1, budget.getAmount());
            preparedStatement.setFloat(2, budget.getAlreadySpent());
            preparedStatement.setInt(3, budget.getMonth());
            preparedStatement.setInt(4, budget.getYear());
            preparedStatement.setInt(5, budget.getCategoryId());
            preparedStatement.setInt(6, budget.getCurrencyId());
            preparedStatement.setInt(7, budget.getOwnerId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean budgetNotExists(int category,int month, int year, int owner){
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM budgets WHERE categoryId  = ? and month =? and year = ? and ownerId= ?"))
        {
            preparedStatement.setInt(1, category);
            preparedStatement.setInt(2, month);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, owner);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("count") == 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Budget get(int budgetId) {
        int id;
        int amount;
        float alreadySpent;
        int month;
        int year;
        int categoryId;
        int currencyId;
        int ownerId;
        String categoryName;
        String currencyName;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM budgets INNER JOIN currencies on budgets.currencyId = currencies.id INNER JOIN categories on budgets.categoryId = categories.id WHERE id = ?"))
        {
            preparedStatement.setInt(1, budgetId);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            amount = resultSet.getInt("amount");
            alreadySpent = resultSet.getFloat("alreadySpent");
            month = resultSet.getInt("month");
            year = resultSet.getInt("year");
            categoryId = resultSet.getInt("categoryId");
            currencyId = resultSet.getInt("currencyId");
            ownerId = resultSet.getInt("ownerId");
            currencyName = resultSet.getString("name");
            categoryName = resultSet.getString(12);
            return new Budget(id,amount,alreadySpent,month,year,categoryId,currencyId,ownerId,categoryName,currencyName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Budget get(int userId,int categorySearch) {
        int id;
        int amount;
        float alreadySpent;
        int month;
        int year;
        int categoryId;
        int currencyId;
        int ownerId;
        String categoryName;
        String currencyName;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM budgets INNER JOIN currencies on budgets.currencyId = currencies.id INNER JOIN categories on budgets.categoryId = categories.id WHERE ownerId = ? and categoryId = ?"))
        {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, categorySearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            amount = resultSet.getInt("amount");
            alreadySpent = resultSet.getFloat("alreadySpent");
            month = resultSet.getInt("month");
            year = resultSet.getInt("year");
            categoryId = resultSet.getInt("categoryId");
            currencyId = resultSet.getInt("currencyId");
            ownerId = resultSet.getInt("ownerId");
            currencyName = resultSet.getString("name");
            categoryName = resultSet.getString(12);
            return new Budget(id,amount,alreadySpent,month,year,categoryId,currencyId,ownerId,categoryName,currencyName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Budget> getBudgets(int userId,LocalDate date) {
        int id;
        int amount;
        int month;
        int year;
        int categoryId;
        int currencyId;
        int ownerId;
        float alreadySpent;
        String categoryName;
        String currencyName;
        List<Budget> budgets = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM budgets INNER JOIN currencies on budgets.currencyId = currencies.id INNER JOIN categories on budgets.categoryId = categories.id where ownerId = ? and month = ? and year =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, date.getMonthValue());
            preparedStatement.setInt(3, date.getYear());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                amount = resultSet.getInt("amount");
                alreadySpent = resultSet.getFloat("alreadySpent");
                month = resultSet.getInt("month");
                year = resultSet.getInt("year");
                categoryId = resultSet.getInt("categoryId");
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("ownerId");
                currencyName = resultSet.getString("name");
                categoryName = resultSet.getString(13);
                budgets.add(new Budget(id,amount,alreadySpent,month,year,categoryId,currencyId,ownerId,categoryName,currencyName));
            }
            return budgets;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addExpense(Expense expense) {
        float expenseInEur = expense.getAmount()*currencyService.get(expense.getCurrencyId()).getPriceInEuro();
        if(budgetNotExists(expense.getCategoryId(),expense.getMonth(),expense.getYear(),expense.getUserId())) return true;
        Budget budget = get(expense.getUserId(),expense.getCategoryId());
        float expenseInBudgetCurrency = expenseInEur/currencyService.get(budget.getCurrencyId()).getPriceInEuro();
        float currentSpent = budget.getAlreadySpent();
        budget.setAlreadySpent(currentSpent+expenseInBudgetCurrency);
        System.out.println("trying to update "+ budget);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update budgets set alreadySpent = ? where id = ?"))
        {

            preparedStatement.setFloat(1, budget.getAlreadySpent());
            preparedStatement.setInt(2, budget.getId());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteExpense(Expense expense) {
        float expenseInEur = expense.getAmount()*currencyService.get(expense.getCurrencyId()).getPriceInEuro();
        if(budgetNotExists(expense.getCategoryId(),expense.getMonth(),expense.getYear(),expense.getUserId())) return true;
        Budget budget = get(expense.getUserId(),expense.getCategoryId());
        float expenseInBudgetCurrency = expenseInEur/currencyService.get(budget.getCurrencyId()).getPriceInEuro();
        float currentSpent = budget.getAlreadySpent();
        if(currentSpent<expenseInBudgetCurrency) return true;
        budget.setAlreadySpent(currentSpent-expenseInBudgetCurrency);
        System.out.println("trying to update "+ budget);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update budgets set alreadySpent = ? where id = ?"))
        {

            preparedStatement.setFloat(1, budget.getAlreadySpent());
            preparedStatement.setInt(2, budget.getId());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int budgetId) {
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("delete FROM budgets WHERE id = ?"))
        {
            preparedStatement.setInt(1, budgetId);
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
