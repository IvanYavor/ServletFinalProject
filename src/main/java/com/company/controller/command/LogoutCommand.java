package com.company.controller.command;

import com.company.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        //TODO delete current user (context and session)
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");

        return "/index.jsp";
    }
}
