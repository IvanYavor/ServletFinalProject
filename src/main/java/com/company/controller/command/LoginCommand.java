package com.company.controller.command;

import com.company.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.company.constant.PageUrlConstants.*;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (login == null || login.equals("") || password == null || password.equals("")) {
            return LOGIN_PAGE;
        }


        if (CommandUtility.checkUserIsLogged(request, login)) {
            CommandUtility.unlogUser(request, login);
            return INDEX_PAGE;
        }

        for (User user : userService.getAllUsers()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword()) && user.getRole() == User.ROLE.USER) {
                CommandUtility.logUser(request, login);
                CommandUtility.setUserRole(request, User.ROLE.USER, login);
                CommandUtility.setUserInfo(user, request);
                session.setAttribute("loggedUser", true);
                return INDEX_PATH;
            } else if (login.equals(user.getLogin()) && password.equals(user.getPassword()) && user.getRole() == User.ROLE.ADMIN) {
                CommandUtility.logUser(request, login);
                CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
                CommandUtility.setUserInfo(user, request);
                session.setAttribute("loggedAdmin", true);
                return INDEX_PATH;
            }
        }

        request.setAttribute("userError", true);

        return LOGIN_PAGE;
    }
}
