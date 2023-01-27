package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/sampleDB";
    private static final String USER = "root";
    private static final String PASS = "11qq11qq";

    public static Connection getConnection() {
        Connection connection;
        try {connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}