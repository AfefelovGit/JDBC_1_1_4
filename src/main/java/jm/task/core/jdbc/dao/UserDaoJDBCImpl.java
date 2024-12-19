package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class UserDaoJDBCImpl implements UserDao {

    // конструктор пустой (условие в задании), хотя можно было бы и по другому
    public UserDaoJDBCImpl() {
    }

    // создать таблицу если она еще не создана, если создана то ничего не делать
    @Override
    public void createUsersTable() {
        // описание запроса на создание таблицы
        String creatTableSQL = """ 
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

        try (Connection connectUtil = Util.getConnection()) {
            Statement statement = connectUtil.createStatement();
            // запрос на создание таблицы к базе данных
            statement.execute(creatTableSQL);
        } catch (SQLException e) {
            System.out.printf("Ошибка при создании новой таблицы.\n%s.", e.getMessage());
        }


    }

    // удаление таблицы если она существует иначе ничего не делать
    @Override
    public void dropUsersTable() {
        // запрос на удаление таблицы
        String deleteTableSQL = "DROP TABLE IF EXISTS mydb.users;";

        try (Connection connectUtil = Util.getConnection()) {

            Statement statement = connectUtil.createStatement();

            // запрос на удаление таблицы
            statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            System.out.printf("Ошибка при удалении таблицы из БД.\n%s.", e.getMessage());
        }
    }

    // добавление строки в таблицу
    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Connection connectUtil = Util.getConnection()) {
            Statement statement = connectUtil.createStatement();
            String sql = format("INSERT INTO Users (firstName, lastName, age) VALUES ('%s', '%s', %d);",
                    name, lastName, (int) age);
            // запрос на добавление новой строки
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("Ошибка при добавлении новой строки в таблицу.\n%s.", e.getMessage());
        }
    }

    // удаление строки из таблицы по id
    @Override
    public void removeUserById(long id) {

        try (Connection connectUtil = Util.getConnection()) {
            Statement statement = connectUtil.createStatement();
            String str = format("DELETE FROM `users` WHERE `id` = %d;", id);
            // запрос на удаление пользователя
            statement.executeUpdate(str);
        } catch (SQLException e) {
            System.out.printf("Ошибка при удалении строки из таблицы.\n%s.", e.getMessage());
        }
    }

    // Удалить все строки в таблице
    @Override
    public void cleanUsersTable() {
        String strCleanUsers = "TRUNCATE TABLE mydb.users;";

        try (Connection connectUtil = Util.getConnection()) {
            Statement statement = connectUtil.createStatement();
            // запрос на очистку всей таблицы
            statement.execute(strCleanUsers);
        } catch (SQLException e) {
            System.out.printf("Ошибка при очистке таблицы.\n%s.", e.getMessage());
        }
    }

    // вывести список всех User в List<User>
    @Override
    public List<User> getAllUsers() {
        List<User> usersMas = new ArrayList<>();
        String sql = "SELECT * FROM mydb.users";

        try (Connection connectUtil = Util.getConnection()) {
            Statement statement = connectUtil.createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
