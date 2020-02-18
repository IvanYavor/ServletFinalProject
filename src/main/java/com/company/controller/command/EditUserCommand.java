package com.company.controller.command;

import com.company.entity.Message;
import com.company.entity.User;
import com.company.service.MessageService;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

import static com.company.constant.PageUrlConstants.ADMIN_HOME_PAGE;
import static com.company.constant.PageUrlConstants.EDIT_USER_PAGE;
import static com.company.constant.ScoreConstants.*;

public class EditUserCommand implements Command {
    private UserService userService;
    private MessageService messageService;

    public EditUserCommand(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String score = request.getParameter("score");
        String id = request.getParameter("id");
        String textMessage = request.getParameter("text_message");
        request.getSession().setAttribute("id", id);
        User user;


        if (score == null || score.equals("")
                || id == null || id.equals("")
                || textMessage == null || textMessage.equals("")) {
            Integer idFromRequest = (Integer) request.getAttribute("id");
            user = userService.getUserById(idFromRequest);
            request.setAttribute("user", user);
            return EDIT_USER_PAGE;
        }

        user = userService.getUserById(Integer.parseInt(id));

        if (!checkScore(Integer.parseInt(score))) {
            request.setAttribute("scoreError", true);
            request.setAttribute("user", user);
            return EDIT_USER_PAGE;
        }
        if (checkIfAccepted(Integer.parseInt(score))) {
            user.setAccepted(true);
        }

        Message message = new Message();
        message.setUserId(Integer.parseInt(id));
        message.setText(textMessage);
        message.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        messageService.createMessage(message);


        user.setTestScore(Integer.parseInt(score));
        userService.updateUser(user);
        return ADMIN_HOME_PAGE;
    }

    private boolean checkScore(int score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }

    private boolean checkIfAccepted(int score) {
        return score >= MIN_ENOUGH_SCORE;
    }
}

