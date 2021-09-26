package com.example;

import com.example.model.entity.*;
import com.example.service.InvoiceService;
import com.example.service.OrderService;
import com.example.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet responsible for order confirmation by client
 */
public class ClientOrderConfirmServlet extends HttpServlet {

    private Logger logger;
    private static final String USER_PARAM = "user";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    /**
     * Method that implements the order confirmation process,
     * including changing the statuses of the order and the associated room,
     * as well as creating an invoice for payment
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException an exception may occur when redirecting a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String orderId = req.getParameter("order");
        String roomId = req.getParameter("room");
        String sum = req.getParameter("sum");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_PARAM);

        if (user != null) {
            if (roomId != null && !roomId.isEmpty() &&
                orderId != null && !orderId.isEmpty() &&
                sum != null && !sum.isEmpty()) {

                RoomService roomService = new RoomService();
                int roomNumber = Integer.parseInt(roomId);
                roomService.refreshStatus(roomNumber, RoomStatus.PENDING_PAYMENT.getName());

                OrderService orderService = new OrderService();
                int orderNumber = Integer.parseInt(orderId);
                orderService.refreshStatus(orderNumber, OrderStatus.PENDING_PAYMENT.getName());

                InvoiceService invoiceService = new InvoiceService();
                Invoice invoice = new Invoice(roomNumber, user.getId(), orderNumber, System.currentTimeMillis(), Integer.parseInt(sum));
                invoiceService.create(invoice);
            }
            resp.sendRedirect("/client-order-list");
        } else {
            resp.sendRedirect("/welcome-page");
        }
    }
}
