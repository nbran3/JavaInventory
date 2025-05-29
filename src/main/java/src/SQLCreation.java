package src;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLCreation {

    public static void createTable() {
        String url = Constants.getDbPath();

        var sql = "CREATE TABLE IF NOT EXISTS warehouse ("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT NOT NULL,"
                + "price REAL NOT NULL,"
                + "quantity INTEGER,"
                + "location TEXT NOT NULL,"
                + "description TEXT,"
                + "expirationDate TEXT,"
                + "sellDate TEXT"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Keep the main method for standalone testing
    public static void main(String[] args) {
        createTable();
    }
}