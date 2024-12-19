package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // данные для подключения к БД
//    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
//    public static Connection connection = null;

    // метод будет статическим и пробрасывать исключение наверх по условию задачи
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        // подключение к базе данных
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

/*
    // закрытие подключения
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }
*/
    // реализуйте настройку соеденения с БД
}
