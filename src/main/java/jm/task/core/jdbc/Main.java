package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Iterator;
import java.util.List;

public class Main {


    public static void main(String[] args) {
/*
        UserDao userDao = new UserDaoJDBCImpl();

        // создание таблицы
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(2);

        List<User> result = userDao.getAllUsers();
        System.out.println(result);
        userDao.cleanUsersTable();


        // удаление таблицы
        userDao.dropUsersTable();
*/

        // создание сервиса для работы с БД
        UserService usersDB = new UserServiceImpl();

        // создание таблицы юсеров
        usersDB.createUsersTable();

        // добавление строк в таблицу
        usersDB.saveUser("Ivan","Ivanov", (byte) 25);
        System.out.println("User с именем Ivan добавлен в базу данных.");

        usersDB.saveUser("Aleksandr","Aleksandrov", (byte) 35);
        System.out.println("User с именем Aleksandr добавлен в базу данных.");

        usersDB.saveUser("Sergej","Sergeev", (byte) 45);
        System.out.println("User с именем Sergej добавлен в базу данных.");

        usersDB.saveUser("Victor","Victorov", (byte) 55);
        System.out.println("User с именем Victor добавлен в базу данных.\n");

        // Выведем список юсеров
        List<User> usersMas = usersDB.getAllUsers();
        for (User userEl : usersMas) {
            System.out.println(userEl);
        }

        // Очистим таблицу
        usersDB.cleanUsersTable();

        // удаление таблицы
        usersDB.dropUsersTable();
    }
}
