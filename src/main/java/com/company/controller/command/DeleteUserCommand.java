package com.company.controller.command;

import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.company.constant.PageUrlConstants.ADMIN_HOME_PAGE;

public class DeleteUserCommand implements Command {
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        return ADMIN_HOME_PAGE;
    }
}
