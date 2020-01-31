package com.company.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public class SessionListener implements HttpSessionListener {
    //
    //private AtomicReference<UserDAO> dao;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashSet<String> loggedUsers = (HashSet<String>) se
                .getSession().getServletContext()
                .getAttribute("loggedUsers");

        String userName = (String) se.getSession().getAttribute("userName");

        loggedUsers.remove(userName);
        se.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
