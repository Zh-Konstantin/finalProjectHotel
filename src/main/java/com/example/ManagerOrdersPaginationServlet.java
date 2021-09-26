package com.example;

import com.example.model.entity.Order;
import com.example.model.entity.Room;
import com.example.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The servlet responsible for receiving the list of orders,
 * as well as pagination on this list
 */
public class ManagerOrdersPaginationServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * the method determines which page from the list of orders is active,
     * and also sets the pagination functionality for the current list of orders
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException an exception can happen when redirecting or forwarding a request
     * @throws IOException an exception can happen when redirecting or forwarding a request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String page = request.getParameter("currentPage");
        int currentPage;
        if (page == null || page.isEmpty())
            currentPage = 1;
        else
            currentPage = Integer.parseInt(page);

        OrderService orderService = new OrderService();
        List<Order> orders = orderService.getOrders();

        int rows = orders.size();

        int nOfPages = rows / RECORDS_PER_PAGE;

        if (nOfPages % RECORDS_PER_PAGE > 0)
            nOfPages++;

        request.setAttribute("orders", getRoomsForPage(orders, currentPage));
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/main/manager/orders");
        dispatcher.forward(request, response);
    }

    /**
     * helper method that selects from the available orders a list for the current page
     * @param orders list of all orders
     * @param currentPage selected page
     * @return sublist of orders for current page
     */
    private List<Order> getRoomsForPage(List<Order> orders, int currentPage) {
        List<Order> ordersOnPage;
        if (orders.size() <= RECORDS_PER_PAGE) {
            ordersOnPage = orders;
        } else {
            int startIndex = (currentPage - 1) * RECORDS_PER_PAGE;
            int endIndex = currentPage * RECORDS_PER_PAGE;
            if (endIndex > orders.size())
                endIndex = orders.size();
            ordersOnPage = orders.subList(startIndex, endIndex);
        }
        return ordersOnPage;
    }
}
