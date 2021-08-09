package database.implementation;

import database.DBAccess;
import database.interfaces.IAccountService;
import database.interfaces.IBudgetService;
import database.interfaces.IExpenseService;
import models.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService implements IExpenseService {

    private IBudgetService budgetService;
    private IAccountService accountService;

    public ExpenseService(IBudgetService budgetService, IAccountService accountService) {
        this.budgetService = budgetService;
        this.accountService = accountService;
    }

    @Override
    public boolean add(Expense expense) {
        boolean addExpense;
        boolean updateBudget;
        boolean updateAccount;
        System.out.println("Trying to add "+expense);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into expenses(amount,description,day,month,year,accountId,categoryId,currencyId,userId) values (?,?,?,?,?,?,?,?,?)"))
        {
            preparedStatement.setFloat(1, expense.getAmount());
            preparedStatement.setString(2, expense.getDescription());
            preparedStatement.setInt(3, expense.getDay());
            preparedStatement.setInt(4, expense.getMonth());
            preparedStatement.setInt(5, expense.getYear());
            preparedStatement.setInt(6, expense.getAccountId());
            preparedStatement.setInt(7, expense.getCategoryId());
            preparedStatement.setInt(8, expense.getCurrencyId());
            preparedStatement.setInt(9, expense.getUserId());
            addExpense = preparedStatement.executeUpdate() > 0;
            updateAccount = accountService.addExpense(expense);
            updateBudget = budgetService.addExpense(expense);
            return addExpense&&updateAccount&&updateBudget;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Expense get(int expenseId) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int categoryId;
        int currencyId;
        int ownerId;
        String accountName;
        String categoryName;
        String currencyName;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expenses INNER JOIN currencies on expenses.currencyId = currencies.id INNER JOIN categories on expenses.categoryId = categories.id inner join accounts on expenses.accountId = accounts.id WHERE expenses.id = ?"))
        {
            preparedStatement.setInt(1, expenseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            amount = resultSet.getFloat("amount");
            description = resultSet.getString("description");
            day = resultSet.getInt("day");
            month = resultSet.getInt("month");
            year = resultSet.getInt("year");
            accountId = resultSet.getInt("accountId");
            categoryId = resultSet.getInt("categoryId");
            currencyId = resultSet.getInt("currencyId");
            ownerId = resultSet.getInt("userId");
            accountName = resultSet.getString(17);
            currencyName = resultSet.getString(12);
            categoryName = resultSet.getString(15);
            return new Expense(id,amount,description,day,month,year,accountId,categoryId,currencyId,ownerId,accountName,currencyName,categoryName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getExpensesDay(int userId,LocalDate date) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int categoryId;
        int currencyId;
        int ownerId;
        String accountName;
        String categoryName;
        String currencyName;
        List<Expense> expenses = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expenses INNER JOIN currencies on expenses.currencyId = currencies.id INNER JOIN categories on expenses.categoryId = categories.id inner join accounts on expenses.accountId = accounts.id where userId = ? and month = ? and year = ? and day =? ");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, date.getMonthValue());
            preparedStatement.setInt(3, date.getYear());
            preparedStatement.setInt(4, date.getDayOfMonth());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                amount = resultSet.getFloat("amount");
                description = resultSet.getString("description");
                day = resultSet.getInt("day");
                month = resultSet.getInt("month");
                year = resultSet.getInt("year");
                accountId = resultSet.getInt("accountId");
                categoryId = resultSet.getInt("categoryId");
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("userId");
                accountName = resultSet.getString(17);
                currencyName = resultSet.getString(12);
                categoryName = resultSet.getString(15);
                expenses.add(new Expense(id,amount,description,day,month,year,accountId,categoryId,currencyId,ownerId,accountName,currencyName,categoryName));
            }
            return expenses;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Expense> getExpensesMonth(int userId,LocalDate date) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int categoryId;
        int currencyId;
        int ownerId;
        String accountName;
        String categoryName;
        String currencyName;
        List<Expense> expenses = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expenses INNER JOIN currencies on expenses.currencyId = currencies.id INNER JOIN categories on expenses.categoryId = categories.id inner join accounts on expenses.accountId = accounts.id where userId = ? and month = ? and year = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, date.getMonthValue());
            preparedStatement.setInt(3, date.getYear());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                amount = resultSet.getFloat("amount");
                description = resultSet.getString("description");
                day = resultSet.getInt("day");
                month = resultSet.getInt("month");
                year = resultSet.getInt("year");
                accountId = resultSet.getInt("accountId");
                categoryId = resultSet.getInt("categoryId");
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("userId");
                accountName = resultSet.getString(17);
                currencyName = resultSet.getString(12);
                categoryName = resultSet.getString(15);
                expenses.add(new Expense(id,amount,description,day,month,year,accountId,categoryId,currencyId,ownerId,accountName,currencyName,categoryName));
            }
            return expenses;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int expenseId) {
        boolean deleteExpense = false;
        boolean updateBudget = false;
        boolean updateAccount = false;
        Expense expense = get(expenseId);
        System.out.println(expense);
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("delete FROM expenses WHERE id = ?"))
        {
            updateAccount = accountService.deleteExpense(expense);
            updateBudget = budgetService.deleteExpense(expense);
            preparedStatement.setInt(1, expenseId);
            deleteExpense =  preparedStatement.executeUpdate() > 0;
            return deleteExpense&&updateAccount&&updateBudget;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
