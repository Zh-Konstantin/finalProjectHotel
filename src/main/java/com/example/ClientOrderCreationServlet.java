package com.example;

import com.example.model.entity.Order;
import com.example.model.entity.RoomClass;
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

/**
 * Servlet responsible for forming a new order by the client
 */
public class ClientOrderCreationServlet extends HttpServlet {

    private Logger logger;
    private static final String USER_PARAM = "user";
    private static final String DAYS_PARAM = "days";
    private static final String PEOPLES_PARAM = "peoples";
    private static final String ROOM_CLASS_PARAM = "roomClass";
    private static final String ORDER_CONFIRMATION = "Заявка успешно отправлена менеджеру. Результат обработки можете посмотреть в меню 'мои заявки'";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    /**
     * method that implements the formation of a new order by the client
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException an exception may occur when forwarding or redirecting a request
     * @throws ServletException an exception may occur when forwarding or redirecting a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String days = req.getParameter(DAYS_PARAM);
        String peoples = req.getParameter(PEOPLES_PARAM);
        String roomClassStr = req.getParameter(ROOM_CLASS_PARAM);

        String errorString2 = null;
        boolean hasError = false;

        if (days == null || days.isEmpty() ||
                peoples == null || peoples.isEmpty() ||
                roomClassStr == null || roomClassStr.isEmpty()) {
            hasError = true;
            errorString2 = "Пожалуйста заполните все параметры заявки (количество дней, количество человек и желаемый клас номера)!";
        } else {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(USER_PARAM);

            OrderService orderService = new OrderService();
            RoomClass roomClass;
            if (roomClassStr.equalsIgnoreCase(RoomClass.LUX.getName()))
                roomClass = RoomClass.LUX;
            else if (roomClassStr.equalsIgnoreCase(RoomClass.STANDARD.getName()))
                roomClass = RoomClass.STANDARD;
            else
                roomClass = RoomClass.ECONOMY;

            Order order = new Order(user.getId(), Integer.parseInt(days), Integer.parseInt(peoples), roomClass);
            orderService.create(order);
        }

        if (hasError) {
            req.setAttribute("errorString2", errorString2);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/main/client");

            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("orderConfirmation", ORDER_CONFIRMATION);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/main/client");

            dispatcher.forward(req, resp);
        }
    }
}
