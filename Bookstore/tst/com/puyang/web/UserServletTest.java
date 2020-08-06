package com.puyang.web;

import com.puyang.dao.BaseDao;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServletTest {
    public static final String USERNAME_NOT_EXIST = "unit_test";
    public static final String PASSWORD_NOT_EXIST = "password";
    public static final String USERNAME_ADMIN = "admin";
    public static final String PASSWORD_ADMIN = "admin";
    public static final String EMAIL = "email";
    public static final String CODE = "code";
    public static final String CONTEXT_PATH = "contextPath";
    public static final String SQL_DELETE = "delete from t_user where username = 'unit_test'";
    public static final Map<String, String[]> MAP_EXIST = createMap(USERNAME_ADMIN, PASSWORD_ADMIN);
    public static final Map<String, String[]> MAP_NOT_EXIST = createMap(USERNAME_NOT_EXIST, PASSWORD_NOT_EXIST);

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession httpSession;
    @Mock
    private PrintWriter printWriter;

    private BaseDao baseDao;
    private UserServlet userServlet;

    @BeforeEach
    public void setUp() {
        userServlet = new UserServlet();
        baseDao = new UserDaoImpl();
    }

    @Test
    public void loginShouldGetNull() throws ServletException, IOException {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(KAPTCHA_SESSION_KEY)).thenReturn(CODE);
        when(httpServletRequest.getParameter("code")).thenReturn(CODE);
        when(httpServletRequest.getParameterMap()).thenReturn(MAP_NOT_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login.jsp")).thenReturn(requestDispatcher);

        userServlet.login(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void loginShouldGetNotNull() throws ServletException, IOException {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(KAPTCHA_SESSION_KEY)).thenReturn(CODE);
        when(httpServletRequest.getParameter("code")).thenReturn(CODE);
        when(httpServletRequest.getParameterMap()).thenReturn(MAP_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/login_success.jsp")).thenReturn(requestDispatcher);

        userServlet.login(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void registerShouldGetNull() throws ServletException, IOException {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(KAPTCHA_SESSION_KEY)).thenReturn(CODE);
        when(httpServletRequest.getParameter("code")).thenReturn(CODE);
        when(httpServletRequest.getParameterMap()).thenReturn(MAP_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/register.jsp")).thenReturn(requestDispatcher);

        userServlet.register(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void registerShouldGetNotNull() throws ServletException, IOException {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(KAPTCHA_SESSION_KEY)).thenReturn(CODE);
        when(httpServletRequest.getParameter("code")).thenReturn(CODE);
        when(httpServletRequest.getParameterMap()).thenReturn(MAP_NOT_EXIST);
        when(httpServletRequest.getRequestDispatcher("/pages/user/register_success.jsp")).thenReturn(requestDispatcher);

        userServlet.register(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        baseDao.update(SQL_DELETE);
    }

    @Test
    public void logout() throws IOException {
        when(httpServletRequest.getContextPath()).thenReturn(CONTEXT_PATH);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        userServlet.logout(httpServletRequest, httpServletResponse);
        verify(httpSession).invalidate();
        verify(httpServletResponse).sendRedirect(CONTEXT_PATH);
    }

    @Test
    public void ajaxExistsUsername() throws IOException {
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        userServlet.ajaxExistsUsername(httpServletRequest, httpServletResponse);
        verify(httpServletResponse).getWriter();
        verify(printWriter).write(any(String.class));
    }

    private static Map<String, String[]> createMap(String username, String password) {
        Map<String, String[]> map = new HashMap<>();
        map.put("username", new String[]{username});
        map.put("password", new String[]{password});
        map.put("email", new String[]{EMAIL});
        return map;
    }
}
