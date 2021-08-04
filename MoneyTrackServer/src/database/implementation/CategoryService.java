package database.implementation;

import database.DBAccess;
import database.interfaces.ICategoryService;
import models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    public boolean nameFree(String name){
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT count(*) as count FROM categories WHERE name = ?"))
        {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.getInt("count") == 0;
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(Category category) {
        System.out.println("trying to add "+category);
        if(!nameFree(category.getName())) return false;

        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories(name) values(?)"))
        {

            preparedStatement.setString(1, category.getName());
            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category get(int categoryId) {
        String name = null;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?"))
        {

            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("name");
            id = resultSet.getInt("id");
            return new Category(id,name);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category get(String categoryName) {
        String name = null;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?"))
        {

            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("name");
            id = resultSet.getInt("id");
            return new Category(id,name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        String name = null;
        int id = 0;
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories"))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                name = resultSet.getString("name");
                id = resultSet.getInt("id");
                categories.add(new Category(id,name));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean update(Category category) {
        System.out.println("trying to update "+category);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update categories set name = ? where id = ?"))
        {

            preparedStatement.setString(1, category.getName());
            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
