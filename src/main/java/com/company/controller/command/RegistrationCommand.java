package com.company.controller.command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");

        if( username == null || username.equals("") || password == null || password.equals("")
            || fullName == null || fullName.equals("")) {
            //System.out.println("Not");
            return "/registration.jsp";
        }

        if(CommandUtility.checkUserIsLogged(request, username)){
            return "/index.jsp";
        }

        //todo add user to db

        //userDAO.save();
        return "/index.jsp";
    }
}
