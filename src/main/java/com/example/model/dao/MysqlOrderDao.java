package com.example.model.dao;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class MysqlOrderDao implements OrderDao {

    private final static Logger LOGGER = LogManager.getLogger(MysqlOrderDao.class.getSimpleName());
    private Connection connection;

    public MysqlOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulRequestException {
        // not needed now
        return false;
    }

    @Override
    public boolean create(Order entity) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders (user_id, days_number, peoples_count, room_class) " +
                        "values (?,?,?,?);")){

            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getDaysNumber());
            statement.setInt(3, entity.getPeoplesCount());
            statement.setString(4, entity.getRoomClass().getName());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public List<Order> getOrdersForUser(int userId) throws UnsuccessfulRequestException {
        List<Order> orders;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders " +
                        "WHERE user_id = ?;")){
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            orders = parseToEntityList(resultSet);
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> getNewOrders() throws UnsuccessfulRequestException {
        List<Order> orders;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders " +
                        "WHERE status = ?;")){
            statement.setString(1, "new");
            resultSet = statement.executeQuery();
            orders = parseToEntityList(resultSet);
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> getOrders() throws UnsuccessfulRequestException {
        List<Order> orders;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders;")){
            resultSet = statement.executeQuery();
            orders = parseToEntityList(resultSet);
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return orders;
    }

    @Override
    public boolean refreshStatus(int orderId, String orderStatus) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE orders " +
                        "SET status = ? " +
                        "WHERE order_id = ?;")){
            statement.setString(1, orderStatus);
            statement.setInt(2, orderId);
            changes = statement.executeUpdate();
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public Order getOrder(int orderId) throws UnsuccessfulRequestException {
        Order order;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders " +
                        "WHERE order_id = ?;")){
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            order = parseSingleEntity(resultSet);
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return order;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    private Order parseSingleEntity(ResultSet rs) throws SQLException {
        List<Order> orders = parseToEntityList(rs);

        if (orders.size() == 0)
            return null;

        return orders.get(0);
    }

    @Override
    public boolean addRoomInfo(int orderId, int roomNumber, double totalSum, RoomClass roomClass) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE orders " +
                        "SET apartment_number = ?, total_sum = ?, room_class = ? " +
                        "WHERE order_id = ?;")){
            statement.setInt(1, roomNumber);
            statement.setDouble(2, totalSum);
            statement.setString(3, roomClass.getName());
            statement.setInt(4, orderId);
            changes = statement.executeUpdate();
        } catch (SQLException e){
            LOGGER.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
        }
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param resultSet ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<Order> parseToEntityList(ResultSet resultSet) throws SQLException {
        List<Order> items = new ArrayList<>();
        Order order;
        while(resultSet.next()){
            order = new Order();
            order.setId(resultSet.getInt("order_id"));
            order.setRoomId(resultSet.getInt("apartment_number"));
            order.setUserId(resultSet.getInt("user_id"));
            order.setDaysNumber(resultSet.getInt("days_number"));
            order.setPeoplesCount(resultSet.getInt("peoples_count"));
            order.setTotalSum(resultSet.getInt("total_sum"));

            String roomClass = resultSet.getString("room_class");
            if (roomClass.equalsIgnoreCase(RoomClass.LUX.getName()))
                order.setRoomClass(RoomClass.LUX);
            else if (roomClass.equalsIgnoreCase(RoomClass.STANDARD.getName()))
                order.setRoomClass(RoomClass.STANDARD);
            else if (roomClass.equalsIgnoreCase(RoomClass.ECONOMY.getName()))
                order.setRoomClass(RoomClass.ECONOMY);

            String status = resultSet.getString("status");
            if (status.equalsIgnoreCase(OrderStatus.NEW.getName()))
                order.setStatus(OrderStatus.NEW);
            else if (status.equalsIgnoreCase(OrderStatus.IN_CONFIRM.getName()))
                order.setStatus(OrderStatus.IN_CONFIRM);
            else if (status.equalsIgnoreCase(OrderStatus.CANCELED.getName()))
                order.setStatus(OrderStatus.CANCELED);
            else if (status.equalsIgnoreCase(OrderStatus.PAID.getName()))
                order.setStatus(OrderStatus.PAID);

            items.add(order);
        }
        return items;
    }
}
