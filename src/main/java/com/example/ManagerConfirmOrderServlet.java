package com.example;

import com.example.model.entity.Order;
import com.example.model.entity.OrderStatus;
import com.example.model.entity.Room;
import com.example.model.entity.RoomStatus;
import com.example.service.OrderService;
import com.example.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet responsible for saving selected Room to order
 * and changing status of order
 */
public class ManagerConfirmOrderServlet extends HttpServlet {

    private Logger logger;
    public static final String ORDER_PARAM = "order";
    public static final String ROOM_ID_PARAM = "room";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    /**
     * the method implements the binding of the selected room to the client's order,
     * including updating the status of the order and the room
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException an exception can happen when redirecting a request
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute(ORDER_PARAM);

        String roomId = req.getParameter(ROOM_ID_PARAM);

        if (order != null && roomId != null && !roomId.isEmpty()) {
            int roomNumber = Integer.parseInt(roomId);

            RoomService roomService = new RoomService();
            roomService.refreshStatus(roomNumber, RoomStatus.NOT_AVAILABLE.getName());
            Room room = roomService.findRoomByNumber(roomNumber);

            OrderService orderService = new OrderService();
            orderService.addRoomInfo(order.getId(), roomNumber, room.getPrice(), room.getRoomClass());
            orderService.refreshStatus(order.getId(), OrderStatus.IN_CONFIRM.getName());
        }
        resp.sendRedirect("/manager-order-pagination");
    }
}
