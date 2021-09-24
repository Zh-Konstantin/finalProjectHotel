package com.example.model.dao.factory;

import com.example.model.dao.*;

import java.sql.Connection;

/**
 * The concrete implementation of the DAOFactory
 * for the MySQL relational database
 */
public class MySqlDAOFactory implements DAOFactory {
    @Override
    public UserDao createUserDao(Connection connection) {
        return new MysqlUserDao(connection);
    }

    @Override
    public InvoiceDao createInvoiceDao(Connection connection) {
        return new MysqlInvoiceDao(connection);
    }

    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new MysqlOrderDao(connection);
    }

    @Override
    public RoomDao createRoomDao(Connection connection) {
        return new MysqlRoomDao(connection);
    }
}
