package com.company.controller.command;

import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        return "/WEB-INF/admin/adminbasis.jsp";
    }
}
