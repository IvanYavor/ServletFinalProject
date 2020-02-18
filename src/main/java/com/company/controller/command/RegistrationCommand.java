package com.company.controller.command;

import com.company.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import static com.company.constant.PageUrlConstants.LOGIN_PAGE;
import static com.company.constant.PageUrlConstants.REGISTRATION_PAGE;

public class RegistrationCommand implements Command {
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
            return REGISTRATION_PAGE;
        }

        if (!Pattern.matches(loginPattern, login)) {
            request.setAttribute("loginError", true);
            return REGISTRATION_PAGE;
        } else if (!Pattern.matches(passwordPattern, password)) {
            request.setAttribute("passwordError", true);
            return REGISTRATION_PAGE;
        } else if (!Pattern.matches(fullNamePattern, fullName)) {
            request.setAttribute("fullNameError", true);
            return REGISTRATION_PAGE;
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
            return REGISTRATION_PAGE;
        }

        return LOGIN_PAGE;
    }
}
