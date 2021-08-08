package database.implementation;

import database.DBAccess;
import database.interfaces.IBudgetService;
import models.Budget;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetService implements IBudgetService {

    @Override
    public boolean add(Budget budget) {
        System.out.println("Trying to add "+budget);
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("budgets(amount,month,year,categoryId,currencyId,ownerId) values (?,?,?,?,?,?)"))
        {
            preparedStatement.setInt(1, budget.getAmount());
            preparedStatement.setInt(2, budget.getMonth());
            preparedStatement.setInt(3, budget.getYear());
            preparedStatement.setInt(4, budget.getCategoryId());
            preparedStatement.setInt(5, budget.getCurrencyId());
            preparedStatement.setInt(6, budget.getOwnerId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Budget get(int budgetId) {
        int id;
        int amount;
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
            month = resultSet.getInt("month");
            year = resultSet.getInt("year");
            categoryId = resultSet.getInt("categoryId");
            currencyId = resultSet.getInt("currencyId");
            ownerId = resultSet.getInt("ownerId");
            currencyName = resultSet.getString("name");
            categoryName = resultSet.getString(12);
            return new Budget(id,amount,month,year,categoryId,currencyId,ownerId,categoryName,currencyName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Budget> getBudgets(int userId) {
        int id;
        int amount;
        int month;
        int year;
        int categoryId;
        int currencyId;
        int ownerId;
        String categoryName;
        String currencyName;
        List<Budget> budgets = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM budgets INNER JOIN currencies on budgets.currencyId = currencies.id INNER JOIN categories on budgets.categoryId = categories.id where ownerId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                amount = resultSet.getInt("amount");
                month = resultSet.getInt("month");
                year = resultSet.getInt("year");
                categoryId = resultSet.getInt("categoryId");
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("ownerId");
                currencyName = resultSet.getString("name");
                categoryName = resultSet.getString(12);
                budgets.add(new Budget(id,amount,month,year,categoryId,currencyId,ownerId,categoryName,currencyName));
            }
            return budgets;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
