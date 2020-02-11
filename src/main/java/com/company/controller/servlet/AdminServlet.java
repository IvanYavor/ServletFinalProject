package com.company.controller.servlet;

import com.company.controller.command.*;
import com.company.model.entity.User;
import com.company.service.MessageService;
import com.company.service.SpecialityService;
import com.company.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class AdminServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();
    private SpecialityService specialityService = new SpecialityService();
    private MessageService messageService = new MessageService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());
        commands.put("listUsers", new ListUsersCommand(userService));
        commands.put("edit", new EditUserCommand(userService, messageService));
        commands.put("createSpeciality", new CreateSpecialityCommand(specialityService));
        commands.put("delete", new DeleteUserCommand(userService));
        commands.put("rating", new RatingCommand(userService));
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

        int id = -1;
        String path = req.getRequestURI();
        path = path.replaceFirst("/admin", "");
        path = path.replaceFirst("/", "");
        String[] res = path.split("/");
        if(res.length > 1) {
            id = Integer.parseInt(res[1]);
            req.setAttribute("id", id);
            path = res[0];
        }
        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/admin/adminbasis.jsp");
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }


}
