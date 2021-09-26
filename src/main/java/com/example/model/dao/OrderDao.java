package com.example.model.dao;


import com.example.model.entity.Order;
import com.example.model.entity.RoomClass;
import com.example.model.entity.User;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface OrderDao extends AbstractDao<Integer, Order> {
    List<Order> getOrders();
    List<Order> getOrdersForUser(int userId);
    List<Order> getNewOrders();
    boolean refreshStatus(int orderId, String orderStatus);
    boolean addRoomInfo(int orderId, int roomNumber, double totalSum, RoomClass roomClass);
    Order getOrder (int orderId);
}
