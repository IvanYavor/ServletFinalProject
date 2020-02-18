package com.company.controller.command;

import com.company.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static com.company.constant.PageUrlConstants.INDEX_PAGE;


public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");

        String username = (String) context.getAttribute("login");

        loggedUsers.remove(username);

        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");

        request.getSession().invalidate();

        return INDEX_PAGE;
    }
}
