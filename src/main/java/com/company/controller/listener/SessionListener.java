package com.company.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        HashSet<String> loggedUsers =
                (HashSet<String>) context.getAttribute("loggedUsers");

        String login = (String) se.getSession().getAttribute("login");
        loggedUsers.remove(login);
        se.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
