package com.company.controller.command;

import com.company.model.entity.Message;
import com.company.model.entity.Speciality;
import com.company.model.entity.User;
import com.company.service.MessageService;
import com.company.service.SpecialityService;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.company.constant.PageUrlConstants.REG_SPECIALITY_PAGE;
import static com.company.constant.PageUrlConstants.USER_HOME_PAGE;

public class RegistrationSpecialityCommand implements Command {
    private UserService userService;
    private SpecialityService specialityService;
    private MessageService messageService;

    public RegistrationSpecialityCommand(SpecialityService specialityService, UserService userService, MessageService messageService) {
        this.specialityService = specialityService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String specialityName = request.getParameter("speciality_name");
        String id = request.getParameter("id");

        if (specialityName == null || specialityName.equals("") || id == null || id.equals("")) {
            request.setAttribute("user", request.getSession().getAttribute("user"));
            request.setAttribute("specialities", specialityService.getAllSpeciality());
            return REG_SPECIALITY_PAGE;
        }


        Speciality s = specialityService.getSpecialityByName(specialityName);
        User user = userService.getUserById(Integer.parseInt(id));
        user.setSpecialityId(s.getId());
        user.setSpeciality(s);
        userService.updateUser(user);
        request.getSession().setAttribute("user", user);
//        Message message =  messageService.getByUserId(Integer.parseInt(id));
//        request.setAttribute("message", message);
        request.setAttribute("user", user);
        return USER_HOME_PAGE;
    }
}
