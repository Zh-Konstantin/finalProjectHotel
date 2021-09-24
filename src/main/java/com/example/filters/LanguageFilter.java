package com.example.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Is triggered when user changer interface language
 */
public class LanguageFilter implements Filter {

    private static final String LANG_PARAM = "lang";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if(request.getParameter(LANG_PARAM)!=null){
            HttpSession session = request.getSession();
            session.setAttribute(LANG_PARAM, request.getParameter(LANG_PARAM));
        }
        chain.doFilter(req, resp);
    }
}
