package com.puyang.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServletTest {
    public static final String USERNAME_NOT_EXIST = "username";
    public static final String PASSWORD_NOT_EXIST = "password";
    public static final String USERNAME_ADMIN = "admin";
    public static final String PASSWORD_ADMIN = "admin";

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;

    private LoginServlet loginServlet;

    @BeforeEach
    public void setUp() {
        loginServlet = new LoginServlet();
    }

    @Test
    public void shouldGetNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_NOT_EXIST);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_NOT_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login.jsp")).thenReturn(requestDispatcher);
        loginServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void shouldGetNotNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_ADMIN);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_ADMIN);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login_success.jsp")).thenReturn(requestDispatcher);
        loginServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }
}