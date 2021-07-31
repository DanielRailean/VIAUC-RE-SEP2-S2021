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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void createUsersTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT ,email varchar(30) unique not null, password varchar(30) not null)");
    }

    private void insertDefaultUser(Statement statement) throws SQLException{
        statement.executeUpdate("INSERT OR IGNORE INTO users(email,password) values ('dd','dd')");
    }
}
