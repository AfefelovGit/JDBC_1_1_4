package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        UserService usersDB = new UserServiceImpl();

        usersDB.createUsersTable();

        usersDB.saveUser("Ivan","Ivanov", (byte) 25);
        System.out.println("User с именем Ivan добавлен в базу данных.");

        usersDB.saveUser("Aleksandr","Aleksandrov", (byte) 35);
        System.out.println("User с именем Aleksandr добавлен в базу данных.");

        usersDB.saveUser("Sergej","Sergeev", (byte) 45);
        System.out.println("User с именем Sergej добавлен в базу данных.");

        usersDB.saveUser("Victor","Victorov", (byte) 55);
        System.out.println("User с именем Victor добавлен в базу данных.\n");

        List<User> usersMas = usersDB.getAllUsers();
        for (User userEl : usersMas) {
            System.out.println(userEl);
        }

        usersDB.cleanUsersTable();

        usersDB.dropUsersTable();
    }
}
