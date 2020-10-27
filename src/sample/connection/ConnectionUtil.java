package sample.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/courses?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9874123";

    public static Connection connDB() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
