package com.example.model.dao;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.entity.Invoice;
import com.example.model.entity.InvoiceStatus;
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
public class MysqlInvoiceDao implements InvoiceDao {

    private final static Logger logger = LogManager.getLogger(MysqlUserDao.class.getSimpleName());
    private Connection connection;

    public MysqlInvoiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulRequestException {
        // not needed now
        return false;
    }

    @Override
    public boolean create(Invoice entity) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO invoice (apartment_number, user_id, order_id, date, sum, status) " +
                        "values (?,?,?,?,?,?);")){

            statement.setInt(1, entity.getRoomId());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getOrderId());
            statement.setLong(4, entity.getDate());
            statement.setDouble(5, entity.getSum());
            statement.setString(6, entity.getStatus().getName());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public boolean createWithoutOrderId(Invoice entity) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO invoice (apartment_number, user_id, date, sum, status) " +
                        "values (?,?,?,?,?);")){

            statement.setInt(1, entity.getRoomId());
            statement.setInt(2, entity.getUserId());
            statement.setLong(3, entity.getDate());
            statement.setDouble(4, entity.getSum());
            statement.setString(5, entity.getStatus().getName());
            changes = statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public boolean refreshStatus(int invoiceId, String status) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE invoice " +
                        "SET status = ? " +
                        "WHERE invoice_id = ?;")){
            statement.setString(1, status);
            statement.setInt(2, invoiceId);
            changes = statement.executeUpdate();
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public List<Invoice> findInvoicesForUser(int userId) throws UnsuccessfulRequestException {
        List<Invoice> invoices;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT invoice_id, apartment_number, user_id, order_id, date, sum, status " +
                        "FROM invoice " +
                        "WHERE user_id = ?;")){
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            invoices = parseToEntityList(resultSet);
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return invoices;
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param resultSet ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<Invoice> parseToEntityList(ResultSet resultSet) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        while (resultSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getInt("invoice_id"));
            invoice.setRoomId(resultSet.getInt("apartment_number"));
            invoice.setUserId(resultSet.getInt("user_id"));
            invoice.setOrderId(resultSet.getInt("order_id"));
            invoice.setDate(resultSet.getLong("date"));
            invoice.setSum(resultSet.getDouble("sum"));

            String status = resultSet.getString("status");
            if (status.equalsIgnoreCase(InvoiceStatus.PENDING_PAYMENT.getName()))
                invoice.setStatus(InvoiceStatus.PENDING_PAYMENT);
            else if (status.equalsIgnoreCase(InvoiceStatus.PAID.getName()))
                invoice.setStatus(InvoiceStatus.PAID);
            else if (status.equalsIgnoreCase(InvoiceStatus.REJECTED.getName()))
                invoice.setStatus(InvoiceStatus.REJECTED);

            invoices.add(invoice);
        }
        return invoices;
    }

    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
