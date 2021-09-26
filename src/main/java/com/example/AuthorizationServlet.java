package com.example;


import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.DBManager;
import com.example.model.entity.User;
import com.example.model.entity.UserRole;
import com.example.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet responsible for routing pages connected to authorization and authentication.
 */
public class AuthorizationServlet extends HttpServlet {

    private Logger logger;
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void init() {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * the method implements the authentication and authorization.
     * Depending on the role, redirects to the user or administrator page.
     * In case of incorrect data, displays an informational message to the user.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException an exception can happen when redirecting or forwarding a request
     * @throws IOException an exception can happen when redirecting or forwarding a request
     */
    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter(EMAIL_PARAM);
        String userPassword = request.getParameter(PASSWORD_PARAM);

        UserService userService = new UserService();
        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (userEmail == null || userPassword == null || userEmail.length() == 0 || userPassword.length() == 0) {
            hasError = true;
            errorString = "Введите адрес почты и пароль!";
        } else {
            try {
                user = userService.findUser(userEmail, userPassword);

                if (user == null) {
                    hasError = true;
                    errorString = "Неверное имя пользователя или пароль";
                }
            } catch (UnsuccessfulRequestException e) {
                logger.error(e);
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            request.setAttribute("errorString", errorString);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/welcome-page");

            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            userService.storeLogginedUser(session, user);

            if (user.getRole().equals(UserRole.MANAGER)) {
                response.sendRedirect(request.getContextPath() + "/manager-order-pagination");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/client-room-pagination");
                dispatcher.forward(request, response);
            }
        }
    }


}
