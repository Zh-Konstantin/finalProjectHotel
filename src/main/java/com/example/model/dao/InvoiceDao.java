package com.example.model.dao;


import com.example.model.entity.Invoice;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface InvoiceDao extends AbstractDao<Integer, Invoice>  {
    boolean refreshStatus(int invoiceId, String status);
    boolean createWithoutOrderId(Invoice entity);
    List<Invoice> findInvoicesForUser(int userId);
}
