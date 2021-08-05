package database.implementation;

import database.DBAccess;
import database.interfaces.IAccountService;
import models.Account;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {

    @Override
    public boolean add(Account account) {
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
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currencies WHERE id = ?"))
        {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            balance = resultSet.getFloat("balance");
            ownerId = resultSet.getInt("ownerId");
            currencyId = resultSet.getInt("currencyId");
            sharedWith = resultSet.getInt("sharedWith");
            return new Account(id,name,balance,ownerId,currencyId,sharedWith);
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
        List<Account> accounts = new ArrayList<>();
        try
        {
            Connection connection = DBAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currencies WHERE ownerId = ? or sharedWith = ?");
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
                accounts.add(new Account(id,name,balance,ownerId,currencyId,sharedWith));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
             PreparedStatement preparedStatement = connection.prepareStatement("update accounts set name= ? balance=? currencyId =? where id =?"))
        {

            preparedStatement.setString(1, account.getName());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setInt(3, account.getCurrencyId());

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
