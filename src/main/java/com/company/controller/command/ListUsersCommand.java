package com.company.controller.command;

import com.company.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.company.constant.PageUrlConstants.ADMIN_LIST_USERS_PAGE;

public class ListUsersCommand implements Command {
    private UserService userService;

    public ListUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAllStudents();
        request.setAttribute("users", users);
        return ADMIN_LIST_USERS_PAGE;
    }
}
