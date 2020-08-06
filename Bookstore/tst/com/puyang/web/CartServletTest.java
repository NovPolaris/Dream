package com.puyang.web;

import com.puyang.types.User;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServletTest {
    private static final User USER_ADMIN = User.builder()
            .username("admin")
            .password("admin")
            .email("admin@atguigu.com")
            .id(1)
            .build();
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession httpSession;

    private CartServlet cartServlet;

    @BeforeEach
    public void setUp() {
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        cartServlet = new CartServlet();
    }

    @Test
    public void testListWithAdminUser() throws ServletException, IOException {
        when(httpSession.getAttribute("user")).thenReturn(USER_ADMIN);
        when(httpServletRequest.getRequestDispatcher(any())).thenReturn(requestDispatcher);
        cartServlet.list(httpServletRequest, httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);
    }

    @Test
    public void testListWithNullUser() throws ServletException, IOException {
        when(httpSession.getAttribute("user")).thenReturn(null);
        cartServlet.list(httpServletRequest, httpServletResponse);
        verify(httpServletResponse).sendRedirect(any());
    }
}