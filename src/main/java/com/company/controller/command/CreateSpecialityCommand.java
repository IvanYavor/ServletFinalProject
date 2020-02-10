package com.company.controller.command;

import com.company.model.entity.Speciality;
import com.company.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;

import static com.company.constant.PageUrlConstants.ADMIN_HOME_PAGE;
import static com.company.constant.PageUrlConstants.CREATE_SPECIALITY_PAGE;

public class CreateSpecialityCommand implements Command {
    private SpecialityService specialityService;

    public CreateSpecialityCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String specialtyName = request.getParameter("specialityName");
        String description = request.getParameter("description");
        String faculty = request.getParameter("faculty");

        if (specialtyName == null || specialtyName.equals("") || description == null || description.equals("")
                || faculty == null || faculty.equals("")) {
            return CREATE_SPECIALITY_PAGE;
        }

        Speciality speciality = new Speciality();
        speciality.setName(specialtyName);
        speciality.setDescription(description);
        speciality.setFaculty(faculty);
        specialityService.createSpeciality(speciality);
        return ADMIN_HOME_PAGE;
    }
}
