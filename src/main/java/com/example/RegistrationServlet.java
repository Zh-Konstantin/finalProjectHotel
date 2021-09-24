package com.example;

import com.example.exceptions.UnsuccessfulRequestException;
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
import java.io.PrintWriter;

/**
*Servlet responsible for registering a new client
 */
public class RegistrationServlet extends HttpServlet {

    private Logger logger;
    private static final String LOGIN_PARAM = "login";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String PASSWORD2_PARAM = "password2";
    private static final String REG_CONFIRMATION = "Поздравляем! Вы успешно зарегистрировались. Перейдите на страницу авторизации для входа.";

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass().getSimpleName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    /**
     * the method implements the registration of a new user.
     * In case of incorrect data, displays an informational message to the user.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException an exception can happen when redirecting or forwarding a request
     * @throws IOException an exception can happen when redirecting or forwarding a request
     */
    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String password2 = request.getParameter(PASSWORD2_PARAM);

        UserService userService = new UserService();
        boolean hasError = false;
        String errorString = null;

        if (login == null || email == null || password == null
                || login.length() == 0 || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Введите логин, адрес почты и пароль!";
        } else if(!password.equals(password2)) {
            hasError = true;
            errorString = "Оба введённых пароля должны быть идентичны!";
        } else if (userService.doesUserWithEmailExist(email)) {
            hasError = true;
            errorString = "Пользователь с таким адресом почты уже зарегистрирован!";
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/reg");

            dispatcher.forward(request, response);
        } else {
            User user = new User(login, email, password, UserRole.CLIENT);
            if (userService.create(user)) {
                request.setAttribute("regConfirmation", REG_CONFIRMATION);

                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher("/reg");

                dispatcher.forward(request, response);
            }
        }
    }
}
