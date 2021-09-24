package com.example.model.dao.factory;

import com.example.model.dao.InvoiceDao;
import com.example.model.dao.OrderDao;
import com.example.model.dao.RoomDao;
import com.example.model.dao.UserDao;

import java.sql.Connection;

/**
 * Basic interface for implementing DAO factories,
 * so that switching between databases would be much easier
 */
public interface DAOFactory {
    UserDao createUserDao(Connection connection);
    InvoiceDao createInvoiceDao(Connection connection);
    OrderDao createOrderDao(Connection connection);
    RoomDao createRoomDao(Connection connection);
}
