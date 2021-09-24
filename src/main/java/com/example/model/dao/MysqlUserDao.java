package com.example.model.dao;

import com.example.model.DBManager;
import com.example.model.entity.User;
import com.example.model.entity.UserRole;
import com.example.exceptions.UnsuccessfulRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class MysqlUserDao implements UserDao {

    private final static Logger logger = LogManager.getLogger(MysqlUserDao.class.getSimpleName());
    private final Connection connection;

    public MysqlUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean doesUserExist(String email, String password) throws UnsuccessfulRequestException {
        ResultSet resultSet = null;
        boolean result;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(user_id) AS total FROM users " +
                        "WHERE email = ? AND password = ? ;")){
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("total") > 0;
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    @Override
    public UserRole getUserRoleByLogin(String login) throws UnsuccessfulRequestException {
        return null;
    }

    @Override
    public boolean doesUserWithEmailExist(String email) throws UnsuccessfulRequestException {
        ResultSet resultSet = null;
        boolean result;
        try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(user_id) AS total FROM users " +
                            "WHERE email = ? ;")){
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("total") > 0;
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }finally {
            closeResultSet(resultSet);
        }
        return result;
    }

    @Override
    public User findUser(String email, String password) throws UnsuccessfulRequestException {
        User user;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id, login, email, password, role " +
                        "FROM users " +
                        "WHERE email = ? AND password = ? ;")){
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            user = parseSingleEntity(resultSet);

        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return user;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    private User parseSingleEntity(ResultSet rs) throws SQLException {
        List<User> users = parseToEntityList(rs);

        if (users.size() == 0)
            return null;

        return users.get(0);
    }


    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param resultSet ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<User> parseToEntityList(ResultSet resultSet) throws SQLException {
        List<User> items = new ArrayList<>();
        User user;
        while(resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            String role = resultSet.getString("role");
            if (role.equals(UserRole.MANAGER.getName()))
                user.setRole(UserRole.MANAGER);
            else if (role.equals(UserRole.CLIENT.getName()))
                user.setRole(UserRole.CLIENT);
            items.add(user);
        }
        return items;
    }

    @Override
    public void storeLogginedUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulRequestException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id=? ;")){
            statement.setInt(1, id);
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes>0;
    }

    @Override
    public boolean create(User entity) throws UnsuccessfulRequestException {
        int changes = 0;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (login, email, password, role) " +
                        "values (?,?,?,?);")){

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getRole().getName());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }
}
