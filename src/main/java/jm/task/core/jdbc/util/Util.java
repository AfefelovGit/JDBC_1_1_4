package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection()  {

        try {
            Driver driver = new Driver();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.printf("Ошибка при подключении к БД.\n%s.", e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection()  {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.printf("Ошибка при отключении от БД.\n%s.", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        connection = null;
    }
}
