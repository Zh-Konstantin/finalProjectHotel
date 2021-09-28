package com.example.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter, which is responsible for checking whether a user is allowed to access
 * current page
 */
public class AuthorizationFilter implements Filter {

    private static final String USER_PARAM = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login";

        if(isLoggedIn(session)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    private boolean isLoggedIn(HttpSession session) {
        return (session!=null
                && session.getAttribute(USER_PARAM)!=null);
    }
}

