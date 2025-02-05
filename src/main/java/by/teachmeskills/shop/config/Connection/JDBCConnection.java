package by.teachmeskills.shop.config.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    private static Connection connection = null;
    private static final String url = "jdbc:postgresql://localhost:5432/databaseforshop";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
