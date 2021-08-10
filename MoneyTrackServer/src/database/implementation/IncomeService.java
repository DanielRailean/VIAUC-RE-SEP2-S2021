package database.implementation;

import database.DBAccess;
import database.interfaces.IAccountService;
import database.interfaces.IBudgetService;
import database.interfaces.IIncomeService;
import models.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeService implements IIncomeService {

    private IAccountService accountService;

    public IncomeService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean add(Income income) {
        boolean addIncome;
        boolean updateAccount;
        System.out.println("Trying to add "+income);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into incomes(amount,description,day,month,year,accountId,currencyId,userId) values (?,?,?,?,?,?,?,?)"))
        {
            preparedStatement.setFloat(1, income.getAmount());
            preparedStatement.setString(2, income.getDescription());
            preparedStatement.setInt(3, income.getDay());
            preparedStatement.setInt(4, income.getMonth());
            preparedStatement.setInt(5, income.getYear());
            preparedStatement.setInt(6, income.getAccountId());
            preparedStatement.setInt(7, income.getCurrencyId());
            preparedStatement.setInt(8, income.getUserId());
            addIncome = preparedStatement.executeUpdate() > 0;
            updateAccount = accountService.addIncome(income);
            return addIncome&&updateAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Income get(int incomeId) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int currencyId;
        int ownerId;
        String accountName;
        String currencyName;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM incomes INNER JOIN currencies on incomes.currencyId = currencies.id inner join accounts on incomes.accountId = accounts.id WHERE incomes.id = ?"))
        {
            preparedStatement.setInt(1, incomeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            amount = resultSet.getFloat("amount");
            description = resultSet.getString("description");
            day = resultSet.getInt("day");
            month = resultSet.getInt("month");
            year = resultSet.getInt("year");
            accountId = resultSet.getInt("accountId");
            currencyId = resultSet.getInt("currencyId");
            ownerId = resultSet.getInt("userId");
            accountName = resultSet.getString(14);
            currencyName = resultSet.getString(11);
            return new Income(id,amount,description,day,month,year,accountId,currencyId,ownerId,accountName,currencyName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Income> getIncomesDay(int userId,LocalDate date) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int currencyId;
        int ownerId;
        String accountName;
        String currencyName;
        List<Income> incomes = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM incomes INNER JOIN currencies on incomes.currencyId = currencies.id inner join accounts on incomes.accountId = accounts.id where userId = ? and month = ? and year = ? and day =? ");
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
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("userId");
                accountName = resultSet.getString(14);
                currencyName = resultSet.getString(11);
                incomes.add(new Income(id,amount,description,day,month,year,accountId,currencyId,ownerId,accountName,currencyName));
            }
            return incomes;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Income> getIncomesMonth(int userId,LocalDate date) {
        int id;
        float amount;
        String description;
        int day;
        int month;
        int year;
        int accountId;
        int currencyId;
        int ownerId;
        String accountName;
        String currencyName;
        List<Income> incomes = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM incomes INNER JOIN currencies on incomes.currencyId = currencies.id inner join accounts on incomes.accountId = accounts.id where userId = ? and month = ? and year = ?");
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
                currencyId = resultSet.getInt("currencyId");
                ownerId = resultSet.getInt("userId");
                accountName = resultSet.getString(14);
                currencyName = resultSet.getString(11);
                incomes.add(new Income(id,amount,description,day,month,year,accountId,currencyId,ownerId,accountName,currencyName));
            }
            return incomes;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int incomeId) {
        boolean deleteIncome;
        boolean updateAccount;
        Income income = get(incomeId);
        System.out.println(income);
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("delete FROM incomes WHERE id = ?"))
        {
            updateAccount = accountService.deleteIncome(income);
            preparedStatement.setInt(1, incomeId);
            deleteIncome =  preparedStatement.executeUpdate() > 0;
            return deleteIncome&&updateAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
