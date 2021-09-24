package com.example.model.dao;


import com.example.model.entity.Order;
import com.example.model.entity.User;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface OrderDao extends AbstractDao<Integer, Order> {
    List<Order> getUserOrders(int userId);
    List<Order> getNewOrder();
    boolean refreshStatus(int orderId, String orderStatus);
    boolean addRoomInfo(int orderId, int roomNumber, double totalSum);
}
