package com.example.service;


import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.DBManager;
import com.example.model.dao.MysqlUserDao;
import com.example.model.dao.UserDao;
import com.example.model.dao.factory.DAOFactory;
import com.example.model.dao.factory.MySqlDAOFactory;
import com.example.model.entity.User;
import com.example.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class UserService {

    private final static Logger LOGGER = LogManager.getLogger(UserService.class.getSimpleName());
    private DAOFactory factory;
    private DBManager manager;

    /**
     * Creates UserService entity with default (MySql) factory
     */
    public UserService() {
        this.factory = new MySqlDAOFactory();
        manager = DBManager.getInstance();
    }


    public boolean doesUserExist(String email, String password) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.doesUserExist(email, password);
    }

    public UserRole getUserRoleByLogin(String login) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.getUserRoleByLogin(login);
    }

    public boolean doesUserWithEmailExist(String email) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.doesUserWithEmailExist(email);
    }

    public User findUser(String userEmail, String userPassword) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.findUser(userEmail, userPassword);
    }

    public void storeLogginedUser(HttpSession session, User user) {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        userDao.storeLogginedUser(session, user);
    }

    public boolean delete(Integer id) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.delete(id);
    }

    public boolean create(User user) throws UnsuccessfulRequestException {
        UserDao userDao = factory.createUserDao(manager.getConnection());
        return userDao.create(user);
    }
}
