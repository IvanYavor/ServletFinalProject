package com.company.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
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
