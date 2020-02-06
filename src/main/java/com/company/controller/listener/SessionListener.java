package com.company.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashSet<String> loggedUsers = (HashSet<String>) se
                .getSession().getServletContext()
                .getAttribute("loggedUsers");

        String login = (String) se.getSession().getAttribute("login");

        loggedUsers.remove(login);
        se.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
