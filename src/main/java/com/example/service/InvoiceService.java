package com.example.service;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.DBManager;
import com.example.model.dao.InvoiceDao;
import com.example.model.dao.factory.DAOFactory;
import com.example.model.dao.factory.MySqlDAOFactory;
import com.example.model.entity.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class InvoiceService {

    private final static Logger LOGGER = LogManager.getLogger(InvoiceService.class.getSimpleName());
    private DAOFactory factory;
    private DBManager manager;

    /**
     * Creates InvoiceService entity with default (MySql) factory
     */
    public InvoiceService() {
        this.factory = new MySqlDAOFactory();
        manager = DBManager.getInstance();
    }

    public boolean create(Invoice entity) throws UnsuccessfulRequestException {
        InvoiceDao invoiceDao = factory.createInvoiceDao(manager.getConnection());
        return invoiceDao.create(entity);
    }

    public boolean createWithoutOrderId(Invoice entity) throws UnsuccessfulRequestException {
        InvoiceDao invoiceDao = factory.createInvoiceDao(manager.getConnection());
        return invoiceDao.createWithoutOrderId(entity);
    }

    public boolean refreshStatus(int invoiceId, String status) throws UnsuccessfulRequestException {
        InvoiceDao invoiceDao = factory.createInvoiceDao(manager.getConnection());
        return invoiceDao.refreshStatus(invoiceId, status);
    }

    public List<Invoice> findInvoicesForUser(int userId) throws UnsuccessfulRequestException {
        InvoiceDao invoiceDao = factory.createInvoiceDao(manager.getConnection());
        return invoiceDao.findInvoicesForUser(userId);
    }
}
