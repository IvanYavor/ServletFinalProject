package com.company.controller.command;

import com.company.model.entity.Speciality;
import com.company.model.entity.User;
import com.company.service.SpecialityService;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationSpecialityCommand implements Command {
    private UserService userService;
    private SpecialityService specialityService;

    public RegistrationSpecialityCommand(SpecialityService specialityService, UserService userService) {
        this.specialityService = specialityService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String specialityName = request.getParameter("speciality_name");
        String id = request.getParameter("id");

        if (specialityName == null || specialityName.equals("") || id == null || id.equals("")) {
            request.setAttribute("user", request.getSession().getAttribute("user"));
            request.setAttribute("specialities", specialityService.getAllSpeciality());
            return "/WEB-INF/user/regSpeciality.jsp";
        }


        Speciality s = specialityService.getSpecialityByName(specialityName);
        User user = userService.getUserById(Integer.parseInt(id));
        user.setSpecialityId(s.getId());
        user.setSpeciality(s);
        userService.updateUser(user);
        request.getSession().setAttribute("user", user);
        request.setAttribute("user", user);
        return "/user/index";
    }
}
