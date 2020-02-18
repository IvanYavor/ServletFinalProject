package com.company.controller.command;

import com.company.entity.User;
import com.company.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersCommandTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;
    @Mock
    private List<User> users;

    @InjectMocks
    private ListUsersCommand command;

    @After
    public void resetMock() {
        reset(request, userService, users);
    }

    @Test
    public void shouldReturnListUserPage() {
        String actual = "/WEB-INF/admin/listUsers.jsp";
        String expected = command.execute(request);

        assertThat(actual, is(expected));
    }
}
