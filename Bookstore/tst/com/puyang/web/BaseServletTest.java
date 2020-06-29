package com.puyang.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseServletTest {
    private static final String WRONG_CODE = "wrongCode";
    public static final String ACTION = "register";
    public static final String PATH = "/pages/user/register.jsp";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void doGet() throws Exception {
        BaseServlet baseServlet = new UserServlet();
        when(request.getParameter("action")).thenReturn(ACTION);
        when(request.getParameter("code")).thenReturn(WRONG_CODE);
        when(request.getRequestDispatcher(PATH)).thenReturn(requestDispatcher);
        baseServlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}
