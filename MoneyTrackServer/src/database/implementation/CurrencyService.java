package database.implementation;

import database.DBAccess;
import database.interfaces.ICurrencyService;
import models.Currency;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService implements ICurrencyService {

    public boolean nameFree(String name){
            try (Connection connection = DBAccess.getInstance().getConnection();
                 PreparedStatement preparedStatement =
                         connection.prepareStatement("SELECT count(*) as count FROM currencies WHERE name = ?"))
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
    public boolean add(Currency currency) {
        System.out.println("trying to add "+currency);
        if(!nameFree(currency.getName())) return false;

        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO currencies(name, priceInEur) values(?, ?)"))
        {

            preparedStatement.setString(1, currency.getName());
            preparedStatement.setFloat(2, currency.getPriceInEuro());

            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Currency get(int currencyId) {
        String name = null;
        float priceInEur = 0;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currencies WHERE id = ?"))
        {

            preparedStatement.setInt(1, currencyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("name");
            priceInEur = resultSet.getFloat("priceInEur");
            id = resultSet.getInt("id");
            return new Currency(id,name,priceInEur);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Currency get(String currencyName) {
        String name = null;
        float priceInEur = 0;
        int id = 0;
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currencies WHERE name = ?"))
        {

            preparedStatement.setString(1, currencyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            name = resultSet.getString("name");
            priceInEur = resultSet.getFloat("priceInEur");
            id = resultSet.getInt("id");
            return new Currency(id,name,priceInEur);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Currency> getAll() {
        String name = null;
        float priceInEur = 0;
        int id = 0;
        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM currencies"))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                name = resultSet.getString("name");
                priceInEur = resultSet.getFloat("priceInEur");
                id = resultSet.getInt("id");
                currencies.add(new Currency(id,name,priceInEur));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return currencies;
    }

    @Override
    public boolean update(Currency currency) {
        System.out.println("trying to update "+currency);
        try (Connection connection = DBAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update currencies set name = ?, priceInEur = ? where id = ?"))
        {

            preparedStatement.setString(1, currency.getName());
            preparedStatement.setFloat(2, currency.getPriceInEuro());
            preparedStatement.setInt(3, currency.getId());
            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int currencyId) {
        try (Connection connection = DBAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("delete  FROM currencies WHERE id = ?"))
        {

            preparedStatement.setInt(1, currencyId);
            boolean result = preparedStatement.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
