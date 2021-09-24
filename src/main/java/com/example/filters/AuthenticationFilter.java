package com.example.filters;

import com.example.model.entity.User;
import com.example.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that is responsible for checking if the user
 * is allowed access to the manager page
 */
public class AuthenticationFilter implements Filter {
    private static final String USER_PARAM = "user";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login";
        String clientURI = request.getContextPath() + "/main/client";

        if(isLoggedIn(session)){
            User user = (User) session.getAttribute(USER_PARAM);
            if(user.getRole() == UserRole.MANAGER){
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect(clientURI);
            }
        } else {
            response.sendRedirect(loginURI);
        }
    }

    boolean isLoggedIn(HttpSession session){
        return (session!=null
                && session.getAttribute(USER_PARAM)!=null);
    }
}
