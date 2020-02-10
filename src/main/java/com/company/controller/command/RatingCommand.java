package com.company.controller.command;

import com.company.model.entity.User;
import com.company.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.constant.PageUrlConstants.RATING_PAGE;

public class RatingCommand implements Command {
    private UserService userService;

    public RatingCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> students = userService.getAllStudents();
        students = students.stream().sorted(
                Comparator.
                        comparing(User::getSpecialityId).
                        thenComparingInt(User::getTestScore).
                        reversed()
        ).collect(Collectors.toList());
        request.setAttribute("students", students);
        return RATING_PAGE;
    }
}
