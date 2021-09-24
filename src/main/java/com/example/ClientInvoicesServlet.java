package com.example;

import com.example.model.entity.Invoice;
import com.example.model.entity.User;
import com.example.service.InvoiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ClientInvoicesServlet extends HttpServlet {

    private Logger logger;
    private static final String USER_PARAM = "user";
    private static final String INVOICE_CONFIRMATION = "";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Method that implements the search for a list of customer accounts
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException an exception may occur when forwarding a request
     * @throws IOException an exception may occur when forwarding a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_PARAM);

        if (user != null) {
            InvoiceService invoiceService = new InvoiceService();
            List<Invoice> invoices = invoiceService.findInvoicesForUser(user.getId());

            req.setAttribute("invoices", invoices);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/main/client/invoices");
            dispatcher.forward(req, resp);
        }
    }
}
