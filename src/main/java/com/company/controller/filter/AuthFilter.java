package com.company.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.company.constant.PageUrlConstants.INDEX_PATH;

public class AuthFilter implements Filter {
    private HttpServletRequest httpServletRequest;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        boolean isLoggedInUser = (session != null && session.getAttribute("loggedUser") != null);
        boolean isLoggedInAdmin = (session != null && session.getAttribute("loggedAdmin") != null);
        String loginURI = httpServletRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpServletRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpServletRequest.getRequestURI().endsWith("login.jsp");

        if (path.startsWith("/admin")) {
            if (isLoggedInAdmin && (isLoginRequest || isLoginPage)) {
                httpServletRequest.getRequestDispatcher(INDEX_PATH).forward(request, response);

            } else if (!isLoggedInAdmin) {
                httpServletRequest.getRequestDispatcher(INDEX_PATH).forward(request, response);

            } else {
                filterChain.doFilter(request, response);
            }
        } else if (path.startsWith("/user")) {
            if (isLoggedInUser && (isLoginRequest || isLoginPage)) {
                httpServletRequest.getRequestDispatcher(INDEX_PATH).forward(request, response);

            } else if (!isLoggedInUser) {
                httpServletRequest.getRequestDispatcher(INDEX_PATH).forward(request, response);

            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}