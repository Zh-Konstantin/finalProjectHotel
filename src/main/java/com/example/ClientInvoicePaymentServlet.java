package com.example;


import com.example.model.entity.InvoiceStatus;
import com.example.model.entity.OrderStatus;
import com.example.model.entity.RoomStatus;
import com.example.service.InvoiceService;
import com.example.service.OrderService;
import com.example.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet responsible for making invoice payments
 */
public class ClientInvoicePaymentServlet extends HttpServlet {

    private Logger logger;

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Method that implements the invoice payment process
     * (including changing the statuses of the invoice and the associated room and order)
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException an exception may occur when redirecting a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String invoiceId = req.getParameter("invoice");
        String roomId = req.getParameter("room");
        String orderId = req.getParameter("order");

        if (invoiceId != null && !invoiceId.isEmpty()) {
            InvoiceService invoiceService = new InvoiceService();
            invoiceService.refreshStatus(Integer.parseInt(invoiceId), InvoiceStatus.PAID.getName());
        }

        if (roomId != null && !roomId.isEmpty()) {
            RoomService roomService = new RoomService();
            roomService.refreshStatus(Integer.parseInt(roomId), RoomStatus.BOOKED.getName());
        }

        if (orderId != null && !orderId.isEmpty()) {
            OrderService orderService = new OrderService();
            orderService.refreshStatus(Integer.parseInt(orderId), OrderStatus.PAID.getName());
        }

        resp.sendRedirect("/client-invoice-list");
    }
}
