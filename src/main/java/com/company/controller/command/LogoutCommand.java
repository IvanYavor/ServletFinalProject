package com.company.controller.command;

import com.company.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;


public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        //todo  delete current user (context & session)

        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");

        String username = (String) request.getSession().getServletContext().getAttribute("login");

        loggedUsers.remove(username);

        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");
        return "/index.jsp";
    }
}
