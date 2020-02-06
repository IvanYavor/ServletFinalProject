package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        //userService.getUser(request.getAttribute());
        Integer id = (Integer) request.getAttribute("id");
        request.getSession().setAttribute("id", id);
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return "/WEB-INF/admin/editUser.jsp";
    }
}
