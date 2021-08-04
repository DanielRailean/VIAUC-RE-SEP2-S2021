package database;

import java.sql.*;


public class DBAccess {
    private static DBAccess dbAccess;

    public synchronized static DBAccess getInstance() {
        if (dbAccess == null) {
            dbAccess = new DBAccess();
        }
        return dbAccess;
    }

    public synchronized Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:MoneyTrack.db");
        } catch (Exception e) {
            throw new RuntimeException("Cannot connect to the DB");
        }
    }

    public static void main(String[] args) {
        DBAccess.getInstance().createTables();
    }

    private DBAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createTables() {
        try (Connection con = getConnection(); Statement statement = con.createStatement())
        {
            createUsersTable(statement);
            insertDefaultUser(statement);
            createAdminsTable(statement);
            insertDefaultAdmins(statement);
            createCurrenciesTable(statement);
            insertDefaultCurrencies(statement);
            createCategoriesTable(statement);
            insertDefaultCategories(statement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void createUsersTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT ,email varchar(30) unique not null, password varchar(30) not null)");
    }

    private void insertDefaultUser(Statement statement) throws SQLException{
        statement.executeUpdate("INSERT OR IGNORE INTO users(email,password) values ('dd@dd.com','ddddd')");
    }
    private void createAdminsTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS administrators(id INTEGER PRIMARY KEY AUTOINCREMENT ,email varchar(30) unique not null, password varchar(30) not null)");
    }
    private void insertDefaultAdmins(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT OR IGNORE INTO administrators(email,password) values ('dd@mt.com','ddddd')");
    }
    private void createCurrenciesTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS currencies(id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(10) unique not null, priceInEur REAL not null)");
    }
    private void insertDefaultCurrencies(Statement statement) throws SQLException{
        statement.executeUpdate("INSERT OR IGNORE INTO currencies(name,priceInEur) values ('EUR', 1),('DKK',0.135),('USD',0.8421)");
    }
    private void createCategoriesTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS categories(id INTEGER PRIMARY KEY AUTOINCREMENT ,name varchar(50) unique not null)");
    }
    private void insertDefaultCategories(Statement statement) throws SQLException{
        statement.executeUpdate("INSERT OR IGNORE INTO categories(name) values ('Food'),('Services'),('Housing')");
    }

}
