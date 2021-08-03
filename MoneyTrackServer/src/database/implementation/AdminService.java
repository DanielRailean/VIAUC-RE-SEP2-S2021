package database.implementation;

import database.DBAccess;
import database.interfaces.IAdminService;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService implements IAdminService {
    @Override
    public boolean emailFree(String email) {
        try (Connection connection = DBAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM administrators WHERE email  = ?"))
        {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.getInt("count") == 0;
            return result;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Failed to execute query.");
        }
    }

    @Override
    public boolean register(User admin) {
        System.out.println(admin.getEmail()+" " + admin.getPassword()+" "+ emailFree(admin.getEmail()));
        if(!emailFree(admin.getEmail())) return false;

        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administrators(email, password) values(?, ?)"))
        {

            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());

            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(User admin) {
        System.out.println(admin.getEmail()+" " + admin.getPassword()+" ");
        String email = null;
        String password = null;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?"))
        {

            preparedStatement.setString(1, admin.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            id = resultSet.getInt("id");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if(admin.getPassword().equals(password)) return new User(email,password,id);
        return new User();
    }
}
