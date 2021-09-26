package com.example;

import com.example.model.entity.Room;
import com.example.model.entity.User;
import com.example.model.entity.UserRole;
import com.example.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * The servlet responsible for receiving the list of fee room,
 * as well as pagination on this list
 */
public class RoomPaginationServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 4;
    private static final String USER_PARAM = "user";
    private Logger logger;

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * the method determines which page from the list of rooms is active,
     * and also sets the pagination functionality for the current list of rooms
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException an exception can happen when redirecting or forwarding a request
     * @throws IOException an exception can happen when redirecting or forwarding a request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String page = request.getParameter("currentPage");
        int currentPage;
        if (page == null || page.isEmpty())
            currentPage = 1;
        else
         currentPage = Integer.parseInt(page);

        RoomService roomService = new RoomService();
        List<Room> rooms = roomService.getFreeRooms();

        int rows = rooms.size();

        int nOfPages = rows / RECORDS_PER_PAGE;

        if (nOfPages % RECORDS_PER_PAGE > 0)
            nOfPages++;

        request.setAttribute("rooms", getRoomsForPage(rooms, currentPage));
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        User user = (User) session.getAttribute(USER_PARAM);
        RequestDispatcher dispatcher;
        if (user.getRole() == UserRole.MANAGER)
            dispatcher = request.getRequestDispatcher("/main/manager/rooms");
        else
            dispatcher = request.getRequestDispatcher("/main/client");

        dispatcher.forward(request, response);
    }

    /**
     * helper method that selects from the available rooms a list for the current page
     * @param rooms list of rooms with free status
     * @param currentPage selected page
     * @return sublist of rooms for current page
     */
    private List<Room> getRoomsForPage(List<Room> rooms, int currentPage) {
        List<Room> roomsOnPage;
        if (rooms.size() <= RECORDS_PER_PAGE) {
            roomsOnPage = rooms;
        } else {
            int startIndex = (currentPage - 1) * RECORDS_PER_PAGE;
            int endIndex = currentPage * RECORDS_PER_PAGE;
            if (endIndex > rooms.size())
                endIndex = rooms.size();
            roomsOnPage = rooms.subList(startIndex, endIndex);
        }
        return roomsOnPage;
    }
}
