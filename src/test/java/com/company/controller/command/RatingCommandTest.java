package com.company.controller.command;

import com.company.entity.User;
import com.company.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class RatingCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserService userService;
    @Mock
    private List<User> students;

    @InjectMocks
    private RatingCommand command;

    @After
    public void resetMock() {
        reset(request, userService, students);
    }

    @Test
    public void executeShouldReturnRatingPage() {
        String actual = "/WEB-INF/rating.jsp";
        String expected = command.execute(request);

        assertThat(actual, is(expected));
    }
}
