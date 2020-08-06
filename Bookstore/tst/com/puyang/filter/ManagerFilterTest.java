package com.puyang.filter;

import com.puyang.types.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerFilterTest {
    public static final String NOT_ADMIN = "notAdmin";
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String LINK = "/pages/user/login.jsp";

    @Mock
    private User user;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private HttpSession httpSession;
    @Mock
    private FilterChain filterChain;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ManagerFilter managerFilter;

    @BeforeEach
    public void setUp() {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(USER)).thenReturn(user);
        managerFilter = new ManagerFilter();
    }

    @Test
    public void testAdmin() throws IOException, ServletException {
        when(user.getUsername()).thenReturn(ADMIN);
        managerFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    public void testNotAdmin() throws IOException, ServletException {
        when(user.getUsername()).thenReturn(NOT_ADMIN);
        when(httpServletRequest.getRequestDispatcher(LINK)).thenReturn(requestDispatcher);
        managerFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        verifyNoInteractions(filterChain);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }
}
