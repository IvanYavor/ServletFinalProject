package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String score = request.getParameter("score");
        String id = request.getParameter("id");

        request.getSession().setAttribute("id", id);
        User user;

        if (score == null || score.equals("") || id == null || id.equals("")) {
            Integer idFromRequest = (Integer) request.getAttribute("id");
            user = userService.getUserById(idFromRequest);
            request.setAttribute("user", user);
            return "/WEB-INF/admin/editUser.jsp";
        }

        user = userService.getUserById(Integer.parseInt(id));

        if (!checkScore(Integer.parseInt(score))) {
            request.setAttribute("scoreError", true);
            request.setAttribute("user", user);
            return "/WEB-INF/admin/editUser.jsp";
        }
        if (checkIfAccepted(Integer.parseInt(score))) {
            user.setAccepted(true);
        }
        user.setTestScore(Integer.parseInt(score));
        userService.updateUser(user);
        return "/WEB-INF/admin/adminbasis.jsp";
    }

    private boolean checkScore(int score) {
        //todo constants
        return score >= 0 && score <= 100;
    }

    private boolean checkIfAccepted(int score) {
        //todo constants
        return score >= 60;
    }
}

