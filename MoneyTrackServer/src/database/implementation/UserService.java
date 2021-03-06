package database.implementation;

import database.DBAccess;
import database.interfaces.IUserService;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IUserService {


    @Override
    public boolean register(User user) {
        System.out.println(user.getEmail()+" " + user.getPassword());

        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(email, password) values(?, ?)"))
        {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(User user) {
        System.out.println("trying to login "+user);
        System.out.println(user.getEmail()+" " + user.getPassword()+" ");
        String email = null;
        String password = null;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?"))
        {

            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            id = resultSet.getInt("id");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if(user.getPassword().equals(password)) return new User(email,password,id);
        return null;
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        System.out.println("trying to update password"+user);
        User toUpdate = login(user);
        if (toUpdate == null) return false;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?"))
        {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, toUpdate.getId());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeEmail(User user, String newEmail) {
        System.out.println("trying to update email"+user);
        User toUpdate = login(user);
        if (toUpdate == null) return false;
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update users set email = ? where id = ?"))
        {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, toUpdate.getId());
            return preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getId(String email) {
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?"))
        {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return 0;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }
}
