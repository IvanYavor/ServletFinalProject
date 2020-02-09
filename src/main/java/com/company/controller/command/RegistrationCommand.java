package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {
    //todo norm regex
    final static String loginPattern = "^[a-z0-9_-]{3,32}$";
    final static String passwordPattern = "^[a-zA-Z0-9]\\w{0,32}$";
    final static String fullNamePattern = "^([a-zA-Z0-9]+|[a-zA-Z0-9]+\\s{1}[a-zA-Z0-9]{1,}|[a-zA-Z0-9]+\\s{1}[a-zA-Z0-9]{3,}\\s{1}[a-zA-Z0-9]{1,})$";
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");

        if (login == null || login.equals("")
                || password == null || password.equals("")
                || fullName == null || fullName.equals("")
                || CommandUtility.checkUserIsLogged(request, login)) {
            return "/registration.jsp";
        }

        if (!Pattern.matches(loginPattern, login)) {
            request.setAttribute("loginError", true);
            return "/registration.jsp";
        } else if (!Pattern.matches(passwordPattern, password)) {
            request.setAttribute("passwordError", true);
            return "/registration.jsp";
        } else if (!Pattern.matches(fullNamePattern, fullName)) {
            request.setAttribute("fullNameError", true);
            return "/registration.jsp";
        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setRole(User.ROLE.USER);

        if (userService.saveUser(user)) {
            request.getSession().setAttribute("UserSave", true);
        } else {
            request.setAttribute("userExists", true);
            return "/registration.jsp";
        }

        return "/login.jsp";
    }
}
