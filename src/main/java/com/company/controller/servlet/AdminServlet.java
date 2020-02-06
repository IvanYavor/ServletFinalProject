package com.company.controller.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.company.controller.command.*;
import com.company.service.UserService;


public class AdminServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());
        commands.put("listUsers", new ListUsersCommand(userService));
        commands.put("edit", new EditUserCommand(userService));
        commands.put("editpost", new EditPostUserCommand(userService));
        commands.put("delete", new DeleteUserCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/admin/adminbasis.jsp").forward(req, resp);
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/admin/adminbasis.jsp").forward(req, resp);
//        processPostRequest(req, resp);
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
