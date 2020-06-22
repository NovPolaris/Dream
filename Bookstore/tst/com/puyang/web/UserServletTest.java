package com.puyang.web;

import com.puyang.dao.impl.BaseDao;
import com.puyang.dao.impl.UserDaoImpl;
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
class UserServletTest {
    public static final String USERNAME_NOT_EXIST = "unit_test";
    public static final String PASSWORD_NOT_EXIST = "password";
    public static final String EMAIL_NOT_EXIST = "email";
    public static final String CODE_NOT_EXIST = "abcde";
    public static final String USERNAME_ADMIN = "admin";
    public static final String PASSWORD_ADMIN = "admin";
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String SQL_DELETE = "delete from t_user where username = 'unit_test'";

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;

    private BaseDao baseDao;
    private UserServlet userServlet;


    @BeforeEach
    public void setUp() {
        userServlet = new UserServlet();
        baseDao = new UserDaoImpl();
    }

    @Test
    public void loginShouldGetNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("request_type")).thenReturn(LOGIN);
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_NOT_EXIST);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_NOT_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login.jsp")).thenReturn(requestDispatcher);
        userServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void loginShouldGetNotNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("request_type")).thenReturn(LOGIN);
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_ADMIN);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_ADMIN);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login_success.jsp")).thenReturn(requestDispatcher);
        userServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void registerShouldGetNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("request_type")).thenReturn(REGISTER);
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_ADMIN);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_ADMIN);
        when(httpServletRequest.getRequestDispatcher("/pages/user/register.jsp")).thenReturn(requestDispatcher);
        userServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void registerShouldGetNotNull() throws ServletException, IOException {
        when(httpServletRequest.getParameter("request_type")).thenReturn(REGISTER);
        when(httpServletRequest.getParameter("username")).thenReturn(USERNAME_NOT_EXIST);
        when(httpServletRequest.getParameter("password")).thenReturn(PASSWORD_NOT_EXIST);
        when(httpServletRequest.getParameter("email")).thenReturn(EMAIL_NOT_EXIST);
        when(httpServletRequest.getParameter("code")).thenReturn(CODE_NOT_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/register_success.jsp")).thenReturn(requestDispatcher);
        userServlet.doPost(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        baseDao.update(SQL_DELETE);
    }
}