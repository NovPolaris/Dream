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
import java.net.URISyntaxException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServletTest {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String CLIENT_INDEX_JSP = "/pages/client/index.jsp";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ClientServlet clientServlet;

    @BeforeEach
    public void setUp() {
        clientServlet = new ClientServlet();
        when(request.getParameter(PAGE_NUMBER)).thenReturn("0");
    }

    @Test
    public void page() throws ServletException, IOException, URISyntaxException {
        when(request.getParameter(PAGE_SIZE)).thenReturn(String.valueOf(2));
        when(request.getRequestDispatcher(CLIENT_INDEX_JSP)).thenReturn(requestDispatcher);
        clientServlet.page(request, response);
        verify(request).getRequestDispatcher(CLIENT_INDEX_JSP);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void pageByPrice() throws ServletException, IOException, URISyntaxException {
        when(request.getParameter(PAGE_SIZE)).thenReturn(String.valueOf(2));
        when(request.getParameter(MIN_VALUE)).thenReturn(String.valueOf(0));
        when(request.getParameter(MAX_VALUE)).thenReturn(String.valueOf(Integer.MAX_VALUE));
        when(request.getRequestDispatcher(CLIENT_INDEX_JSP)).thenReturn(requestDispatcher);
        clientServlet.pageByPrice(request, response);
        verify(request).getRequestDispatcher(CLIENT_INDEX_JSP);
        verify(requestDispatcher).forward(request, response);
    }
}