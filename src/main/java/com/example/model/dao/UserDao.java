package com.example.model.dao;

import com.example.model.entity.User;
import com.example.model.entity.UserRole;
import com.example.exceptions.UnsuccessfulRequestException;

import javax.servlet.http.HttpSession;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface UserDao extends AbstractDao<Integer, User> {

    boolean doesUserExist(String login, String password) throws UnsuccessfulRequestException;
    UserRole getUserRoleByLogin(String login) throws UnsuccessfulRequestException;
    boolean doesUserWithEmailExist(String email) throws UnsuccessfulRequestException;
    User findUser(String userEmail, String userPassword);
    void storeLogginedUser(HttpSession session, User user);
    //long getUserIdByEmail(String email) throws UnsuccessfulRequestException;

}
