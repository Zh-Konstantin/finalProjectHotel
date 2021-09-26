package com.example;

import com.example.model.entity.Order;
import com.example.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet responsible for saving selected Order to session
 */
public class ManagerSaveOrderServlet extends HttpServlet {

    private Logger logger;
    public static final String ORDER_ID_PARAM = "orderId";
    public static final String ORDER_PARAM = "order";

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter(ORDER_ID_PARAM);

        if (orderId != null && orderId.length() != 0) {
            OrderService orderService = new OrderService();
            Order selectedOrder = orderService.getOrder(Integer.parseInt(orderId));

            HttpSession session = req.getSession();
            session.setAttribute(ORDER_PARAM, selectedOrder);

            resp.sendRedirect("/manager-room-pagination");
        }
    }
}
