package database.implementation;

import database.DBAccess;
import database.interfaces.IAccountService;
import database.interfaces.ICurrencyService;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    private ICurrencyService currencyService;

    public AccountService(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public boolean add(Account account) {
        if(!accountNotExists(account)) return false;
        System.out.println("Trying to add "+account);
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accounts(name,balance,currencyId,ownerId,sharedWith) values (?,?,?,?,?)"))
        {

            preparedStatement.setString(1, account.getName());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setInt(3, account.getCurrencyId());
            preparedStatement.setInt(4, account.getOwnerId());
            preparedStatement.setInt(5, account.getSharedWith());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account get(int accountId) {
        String name;
        float balance;
        int id;
        int currencyId;
        int ownerId;
        int sharedWith;
        String sharedEmail;
        String currencyName;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN currencies on accounts.currencyId = currencies.id INNER JOIN users on accounts.sharedWith = users.id WHERE accounts.id = ?"))
        {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            balance = resultSet.getFloat("balance");
            ownerId = resultSet.getInt("ownerId");
            currencyId = resultSet.getInt("currencyId");
            sharedWith = resultSet.getInt("sharedWith");
            currencyName = resultSet.getString(8);
            sharedEmail = resultSet.getString("email");
            return new Account(id,name,balance,ownerId,currencyId,sharedWith,currencyName,sharedEmail);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAccounts(int userId) {
        String name;
        float balance;
        int id;
        int currencyId;
        int ownerId;
        int sharedWith;
        String sharedEmail;
        String currencyName;
        List<Account> accounts = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts INNER JOIN currencies on accounts.currencyId = currencies.id INNER JOIN users on accounts.sharedWith = users.id WHERE ownerId =? or sharedWith =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                balance = resultSet.getFloat("balance");
                ownerId = resultSet.getInt("ownerId");
                currencyId = resultSet.getInt("currencyId");
                sharedWith = resultSet.getInt("sharedWith");
                currencyName = resultSet.getString(8);
                sharedEmail = resultSet.getString("email");
                accounts.add(new Account(id,name,balance,ownerId,currencyId,sharedWith,currencyName,sharedEmail));
            }
            return accounts;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean accountNotExists(Account account){
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM accounts WHERE name  = ? and ownerId =?"))
        {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setInt(2, account.getOwnerId());
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
    public boolean shareWith(int accountId, int shareWith) {
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set sharedWith = ? where id =?"))
        {

            preparedStatement.setInt(1, shareWith);
            preparedStatement.setInt(2, accountId);

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean update(Account account) {
        System.out.println("Trying to update "+account);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set name= ?, balance = ? ,currencyId =? where id =?"))
        {

            preparedStatement.setString(1, account.getName());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setInt(3, account.getCurrencyId());
            preparedStatement.setInt(4,account.getId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addExpense(Expense expense) {
        float expenseInEur = expense.getAmount()*currencyService.get(expense.getCurrencyId()).getPriceInEuro();
        Account account = get(expense.getAccountId());
        float expenseInAccountCurrency = expenseInEur/currencyService.get(account.getCurrencyId()).getPriceInEuro();
        float accountBalance = account.getBalance();
        account.setBalance(accountBalance-expenseInAccountCurrency);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set name= ?, balance = ? ,currencyId =? where id =?"))
        {

            preparedStatement.setString(1, account.getName());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setInt(3, account.getCurrencyId());
            preparedStatement.setInt(4,account.getId());

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
        Account account = get(expense.getAccountId());
        float expenseInAccountCurrency = expenseInEur/currencyService.get(account.getCurrencyId()).getPriceInEuro();
        float accountBalance = account.getBalance();
        account.setBalance(accountBalance+expenseInAccountCurrency);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set name= ?, balance = ? ,currencyId =? where id =?"))
        {

            preparedStatement.setString(1, account.getName());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setInt(3, account.getCurrencyId());
            preparedStatement.setInt(4,account.getId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addIncome(Income income) {
        float incomeInEur = income.getAmount()*currencyService.get(income.getCurrencyId()).getPriceInEuro();
        Account account = get(income.getAccountId());
        float incomeInAccountCurrency = incomeInEur/currencyService.get(account.getCurrencyId()).getPriceInEuro();
        float accountBalance = account.getBalance();
        account.setBalance(accountBalance+incomeInAccountCurrency);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set balance = ? where id =?"))
        {
            
            preparedStatement.setFloat(1, account.getBalance());
            preparedStatement.setInt(2,account.getId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteIncome(Income income) {
        float incomeInEur = income.getAmount()*currencyService.get(income.getCurrencyId()).getPriceInEuro();
        Account account = get(income.getAccountId());
        float incomeInAccountCurrency = incomeInEur/currencyService.get(account.getCurrencyId()).getPriceInEuro();
        float accountBalance = account.getBalance();
        account.setBalance(accountBalance-incomeInAccountCurrency);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set balance = ? where id =?"))
        {

            preparedStatement.setFloat(1, account.getBalance());
            preparedStatement.setInt(2,account.getId());

            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int accountId) {
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("delete FROM accounts WHERE id = ?"))
        {
            preparedStatement.setInt(1, accountId);
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
