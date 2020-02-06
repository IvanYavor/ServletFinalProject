package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditPostUserCommand implements Command {
    private UserService userService;

    public EditPostUserCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String id =  request.getParameter("id");
        String login = (String) request.getParameter("login");
        User user = new User();
        user.setLogin(login);
        user.setId(Integer.parseInt(id));
        user.setLogin(login);
        if(userService.updateUser(user)) {
            return "/WEB-INF/admin/adminbasis.jsp";
        }
        request.setAttribute("loginError", true);
        return "/admin/edit/" + id;
    }
}
