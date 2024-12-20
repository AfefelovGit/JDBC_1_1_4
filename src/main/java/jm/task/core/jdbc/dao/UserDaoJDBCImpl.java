package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final static String CREAT_TABLE_SQL = """ 
            CREATE TABLE IF NOT EXISTS `mydb`.`users` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `firstName` VARCHAR(45) NOT NULL,
              `lastName` VARCHAR(45) NOT NULL,
              `age` INT(3) UNSIGNED NOT NULL,
              PRIMARY KEY (`id`),
              UNIQUE INDEX `idnew_table_UNIQUE` (`id` ASC) VISIBLE)
            ENGINE = InnoDB
            DEFAULT CHARACTER SET = utf8;
            """;

    private final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREAT_TABLE_SQL);
        } catch (SQLException e) {
            System.out.printf("Ошибка при создании новой таблицы.\n%s.", e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS mydb.users;");
        } catch (SQLException e) {
            System.out.printf("Ошибка при удалении таблицы из БД.\n%s.", e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO mydb.users (firstName, lastName, age) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Ошибка при добавлении новой строки в таблицу.\n%s.", e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM mydb.users WHERE id = ?")) {
            preparedStatement.setInt(1,(int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Ошибка при удалении строки из таблицы.\n%s.", e.getMessage());
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE mydb.users;");
        } catch (SQLException e) {
            System.out.printf("Ошибка при очистке таблицы.\n%s.", e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersMas = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.users");
            while (rs.next()) {
                User userEl = new User();
                userEl.setId(rs.getLong("id"));
                userEl.setName(rs.getString("firstName"));
                userEl.setLastName(rs.getString("lastName"));
                userEl.setAge(Byte.valueOf(rs.getString("age")));
                usersMas.add(userEl);
            }
        } catch (SQLException e) {
            System.out.printf("Ошибка при чтении всей таблицы\n%s.", e.getMessage());
        }
        return usersMas;
    }
}
