package com.company.controller.command;

import com.company.model.entity.Speciality;
import com.company.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;

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
            return "/WEB-INF/admin/createSpeciality.jsp";
        }

        Speciality speciality = new Speciality();
        speciality.setName(specialtyName);
        speciality.setDescription(description);
        speciality.setFaculty(faculty);
        specialityService.createSpeciality(speciality);
        return "/WEB-INF/admin/adminbasis.jsp";


    }
}
