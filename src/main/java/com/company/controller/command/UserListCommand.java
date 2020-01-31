package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand implements Command {
    UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        return "/userlist.jsp";
    }
}
