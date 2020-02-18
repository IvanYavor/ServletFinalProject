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


public class MainServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();


    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand(userService));
        commands.put("exception", new ExceptionCommand());
        commands.put("registration", new RegistrationCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        path = path.replaceFirst("/", "");
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(req);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
