package com.example.service;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.DBManager;
import com.example.model.dao.OrderDao;
import com.example.model.dao.factory.DAOFactory;
import com.example.model.dao.factory.MySqlDAOFactory;
import com.example.model.entity.Order;
import com.example.model.entity.RoomClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class OrderService {

    private final static Logger LOGGER = LogManager.getLogger(OrderService.class.getSimpleName());
    private DAOFactory factory;
    private DBManager manager;

    /**
     * Creates OrderService entity with default (MySql) factory
     */
    public OrderService() {
        this.factory = new MySqlDAOFactory();
        manager = DBManager.getInstance();
    }

    public boolean create(Order entity) throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.create(entity);
    }

    public List<Order> getOrdersForUser(int userId) throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.getOrdersForUser(userId);
    }

    public List<Order> getNewOrders() throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.getNewOrders();
    }

    public List<Order> getOrders() throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.getOrders();
    }

    public boolean refreshStatus(int orderId, String orderStatus) throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.refreshStatus(orderId, orderStatus);
    }

    public boolean addRoomInfo(int orderId, int roomNumber, double totalSum,  RoomClass roomClass) throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.addRoomInfo(orderId, roomNumber, totalSum, roomClass);
    }

    public Order getOrder(int orderId) throws UnsuccessfulRequestException {
        OrderDao orderDao = factory.createOrderDao(manager.getConnection());
        return orderDao.getOrder(orderId);
    }
}
