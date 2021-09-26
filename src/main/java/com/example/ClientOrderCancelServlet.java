package com.example;

import com.example.model.entity.Invoice;
import com.example.model.entity.OrderStatus;
import com.example.model.entity.RoomStatus;
import com.example.model.entity.User;
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
 * Servlet responsible for order cancellation
 */
public class ClientOrderCancelServlet extends HttpServlet {

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
     * Method that implements the order cancellation process,
     * including changing the statuses of the order and the associated room
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException an exception may occur when redirecting a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String orderId = req.getParameter("order");
        String roomId = req.getParameter("room");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_PARAM);

        if (user != null) {
            if (roomId != null && !roomId.isEmpty() &&
                    orderId != null && !orderId.isEmpty()) {

                RoomService roomService = new RoomService();
                int roomNumber = Integer.parseInt(roomId);
                roomService.refreshStatus(roomNumber, RoomStatus.FREE.getName());

                OrderService orderService = new OrderService();
                int orderNumber = Integer.parseInt(orderId);
                orderService.refreshStatus(orderNumber, OrderStatus.CANCELED.getName());
            }
            resp.sendRedirect("/client-order-list");
        } else {
            resp.sendRedirect("/welcome-page");
        }
    }
}
