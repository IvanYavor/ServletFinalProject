package com.company.controller.servlet;

import com.company.controller.command.*;
import com.company.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());

        commands.put("editUsers", new ListUsersCommand(userService));
//        commands.put("login", new LoginCommand(userService));
//        commands.put("exception", new ExceptionCommand());
//        commands.put("registration", new RegistrationCommand(userService));
//        commands.put("userlist", new UserListCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/userbasis.jsp").forward(req, resp);
//        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/user/userbasis.jsp").forward(req, resp);
//        processRequest(req, resp);
    }

//    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String path = req.getRequestURI();
//        path = path.replaceFirst("/", "");
//        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/admin/adminbasis.jsp");
//        String page = command.execute(req);
//
//        req.getRequestDispatcher(page).forward(req, resp);
//    }
}
