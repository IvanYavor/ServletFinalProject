package com.company.controller.servlet;

import com.company.controller.command.Command;
import com.company.controller.command.RegistrationSpecialityCommand;
import com.company.service.SpecialityService;
import com.company.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();
    private SpecialityService specialityService = new SpecialityService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        commands.put("regSpeciality", new RegistrationSpecialityCommand(specialityService, userService));
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
        //todo optimization and constants

        String path = req.getRequestURI();
        path = path.replaceFirst("/user/", "");
        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/user/userbasis.jsp");
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
