package com.example;

import com.example.model.entity.Order;
import com.example.model.entity.User;
import com.example.service.OrderService;
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


/**
 * Servlet responsible for displaying a list of client's orders
 */
public class ClientOrdersServlet extends HttpServlet {

    private Logger logger;
    private static final String USER_PARAM = "user";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Method that implements the search for a list of client's orders
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException an exception may occur when redirecting or forwarding a request
     * @throws IOException an exception may occur when redirecting or forwarding a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_PARAM);

        if (user != null) {
            OrderService orderService = new OrderService();
            List<Order> orders = orderService.getOrdersForUser(user.getId());

            req.setAttribute("orders", orders);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/main/client/orders");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/welcome-page");
        }
    }
}
