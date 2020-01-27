package com.company.controller.command;

import com.company.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String name =request.getParameter("name");
        String pass = request.getParameter("pass");

        if(name == null || name.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }

        //TODO check login with DB

        if(CommandUtility.checkUserIsLogged(request, name)) {
            return "/WEB-INF/error.jsp";
        }

        if(name.equals("Admin")) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            return "/WEB-INF/admim/adminbasis.jsp";
        } else if(name.equals("User")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            return "/login.jsp";
        }
    }
}
