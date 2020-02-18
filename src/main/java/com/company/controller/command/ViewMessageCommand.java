package com.company.controller.command;

import com.company.entity.Message;
import com.company.entity.User;
import com.company.service.MessageService;

import javax.servlet.http.HttpServletRequest;

public class ViewMessageCommand implements Command {
    private MessageService messageService;

    public ViewMessageCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Message message = messageService.getByUserId(user.getId());
        request.setAttribute("message", message);
        return "/WEB-INF/user/message.jsp";
    }
}
