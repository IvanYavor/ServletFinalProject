package com.company.controller.command;

import com.company.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if( username == null || username.equals("") || password == null || password.equals("")  ){
            //System.out.println("Not");
            return "/login.jsp";
        }
        System.out.println(username + " " + password);
        //System.out.println("Yes!");
//todo: check login with DB

        if(CommandUtility.checkUserIsLogged(request, username)){
            return "/index.jsp";
        }

        if (username.equals("Admin")){
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, username);
            return "/WEB-INF/admin/adminbasis.jsp";
        } else if(username.equals("User")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, username);
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, username);
            return "/login.jsp";
        }


    }

}
