package com.puyang.web;

import com.puyang.dao.BaseDao;
import com.puyang.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.AfterEach;
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
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServletTest {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String CONTEXT_PATH = "ContextPath";
    public static final String PAGE_SIZE = "pageSize";
    public static final String BOOK_MANAGER_JSP = "/pages/manager/book_manager.jsp";
    public static final String SQL_DELETE = "delete from t_book where name = 'unit_test'";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private BookServlet bookServlet;
    private BaseDao baseDao;

    @BeforeEach
    public void setUp() throws IOException {
        bookServlet = new BookServlet();
        baseDao = new UserDaoImpl();
        when(request.getParameterMap()).thenReturn(createMap());
        when(request.getParameter(PAGE_NUMBER)).thenReturn("0");
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);
        bookServlet.add(request, response);
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE);
    }

    @Test
    public void page() throws ServletException, IOException, URISyntaxException {
        when(request.getParameter(PAGE_SIZE)).thenReturn(String.valueOf(2));
        when(request.getRequestDispatcher(BOOK_MANAGER_JSP)).thenReturn(requestDispatcher);
        bookServlet.page(request, response);
    }

    private static Map<String, String[]> createMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("name", new String[]{"unit_test"});
        map.put("author", new String[]{"author"});
        map.put("price", new String[]{"1.0"});
        map.put("sales", new String[]{"2"});
        map.put("stock", new String[]{String.valueOf(3)});
        map.put("imgPath", new String[]{"imgPath"});
        return map;
    }
}