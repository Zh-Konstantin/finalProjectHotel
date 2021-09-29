package com.example;

import com.example.model.entity.Invoice;
import com.example.model.entity.Room;
import com.example.model.entity.RoomStatus;
import com.example.model.entity.User;
import com.example.service.InvoiceService;
import com.example.service.RoomService;
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
 * The servlet responsible for booking a room by the client.
 */
public class ClientRoomBookingServlet extends HttpServlet {

    private Logger logger;
    private static final String ROOM_NUMBER = "room";
    private static final String INVOICE_CONFIRMATION = "Заявка на бронирование успешно отправлена! Пожалуйста оплатите счет.";

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * a method for booking a room
     * and creating an invoice for payment.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException an exception may occur when forwarding a request
     * @throws IOException an exception may occur when forwarding a request
     */
    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomNumber = Integer.parseInt(request.getParameter(ROOM_NUMBER));

        RoomService roomService = new RoomService();
        Room room = roomService.findRoomByNumber(roomNumber);

        if (room == null) {
            //handle error
        } else {
            InvoiceService invoiceService = new InvoiceService();
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            //new invoice: without order id
            Invoice invoice = new Invoice();
            invoice.setRoomId(room.getApartmentNumber());
            invoice.setUserId(user.getId());
            invoice.setSum(room.getPrice());
            //new invoice: default status - PENDING_PAYMENT
            invoice.setDate(System.currentTimeMillis());

            if (invoiceService.createWithoutOrderId(invoice)) {
                roomService.refreshStatus(room.getApartmentNumber(), RoomStatus.PENDING_PAYMENT.getName());
                request.setAttribute("invoiceConfirmation", INVOICE_CONFIRMATION);

                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher("/main/client");

                dispatcher.forward(request, response);
            }
        }

    }
}
