package com.puyang.web;

import com.puyang.dao.BaseDao;
import com.puyang.dao.impl.UserDaoImpl;
import com.puyang.pojo.Book;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServletTest {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String CONTEXT_PATH = "ContextPath";
    public static final String PAGE_LIST = "/manager/bookServlet?action=page&pageNumber=";
    public static final String PAGE_SIZE = "pageSize";
    public static final String BOOK_MANAGER_JSP = "/pages/manager/book_manager.jsp";
    public static final String SQL_SELECT = "select * from t_book where name = 'unit_test'";
    public static final String SQL_DELETE = "delete from t_book where name = 'unit_test'";
    public static final String BOOK_EDIT_JSP = "/pages/manager/book_edit.jsp";
    public static final int NEW_STOCK = 100;
    public static final int ORIGINAL_STOCK = 3;

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
        when(request.getParameterMap()).thenReturn(createMap(ORIGINAL_STOCK, null));
        when(request.getParameter(PAGE_NUMBER)).thenReturn("0");
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);
        bookServlet.add(request, response);
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE);
    }

    @Test
    public void add() throws IOException {
        verify(response).sendRedirect(CONTEXT_PATH + PAGE_LIST + 1);
    }

    @Test
    public void delete() throws IOException {
        Book book = baseDao.queryForOne(Book.class, SQL_SELECT);
        when(request.getParameter("id")).thenReturn(String.valueOf(book.getId()));
        bookServlet.delete(request, response);
        verify(response).sendRedirect(CONTEXT_PATH + PAGE_LIST + 1);
        assertNull(baseDao.queryForOne(Book.class, SQL_SELECT));
    }

    @Test
    public void update() throws IOException {
        Book book = baseDao.queryForOne(Book.class, SQL_SELECT);
        when(request.getParameterMap()).thenReturn(createMap(NEW_STOCK, book.getId()));
        bookServlet.update(request, response);
        verify(response).sendRedirect(CONTEXT_PATH + PAGE_LIST + 1);
        assertEquals(Integer.valueOf(NEW_STOCK), baseDao.queryForOne(Book.class, SQL_SELECT).getStock());
    }

    @Test
    public void edit() throws IOException, ServletException {
        Book book = baseDao.queryForOne(Book.class, SQL_SELECT);
        when(request.getParameter("id")).thenReturn(String.valueOf(book.getId()));
        when(request.getRequestDispatcher(BOOK_EDIT_JSP)).thenReturn(requestDispatcher);
        bookServlet.edit(request, response);
        verify(request).setAttribute("book", book);
        verify(request).getRequestDispatcher(BOOK_EDIT_JSP);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void list() throws ServletException, IOException {
        when(request.getRequestDispatcher(BOOK_MANAGER_JSP)).thenReturn(requestDispatcher);
        bookServlet.list(request, response);
    }

    @Test
    public void page() throws ServletException, IOException, URISyntaxException {
        when(request.getParameter(PAGE_SIZE)).thenReturn(String.valueOf(2));
        when(request.getRequestDispatcher(BOOK_MANAGER_JSP)).thenReturn(requestDispatcher);
        bookServlet.page(request, response);
    }

    private static Map<String, String[]> createMap(int stock, Integer id) {
        Map<String, String[]> map = new HashMap<>();
        map.put("id", new String[]{String.valueOf(id)});
        map.put("name", new String[]{"unit_test"});
        map.put("author", new String[]{"author"});
        map.put("price", new String[]{"1.0"});
        map.put("sales", new String[]{"2"});
        map.put("stock", new String[]{String.valueOf(stock)});
        map.put("imgPath", new String[]{"imgPath"});
        return map;
    }
}
