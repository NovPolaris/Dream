package com.puyang.web;

import com.puyang.types.Book;
import com.puyang.types.Page;
import com.puyang.service.BookService;
import com.puyang.service.impl.BookServiceImpl;
import com.puyang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientServlet extends BaseServlet {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE = "page";
    public static final String SERVLET_URI = "client/clientServlet?action=page";
    public static final String CLIENT_INDEX_JSP = "/pages/client/index.jsp";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";

    private final BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        int pageNumber = WebUtils.parseInt(req.getParameter(PAGE_NUMBER), 1);
        int pageSize = WebUtils.parseInt(req.getParameter(PAGE_SIZE), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.queryBooksInCurrentPage(pageNumber, pageSize);
        bookPage.setUri(new URI(SERVLET_URI));
        req.setAttribute(PAGE, bookPage);
        req.getRequestDispatcher(CLIENT_INDEX_JSP).forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        int pageNumber = WebUtils.parseInt(req.getParameter(PAGE_NUMBER), 1);
        int pageSize = WebUtils.parseInt(req.getParameter(PAGE_SIZE), Page.PAGE_SIZE);
        int minValue = WebUtils.parseInt(req.getParameter(MIN_VALUE), 0);
        int maxValue = WebUtils.parseInt(req.getParameter(MAX_VALUE), Integer.MAX_VALUE);
        Page<Book> bookPage = bookService.queryBooksInCurrentPageByPrice(pageNumber, pageSize, minValue, maxValue);
        bookPage.setUri(new URI("client/clientServlet?action=pageByPrice&minValue=" + minValue + "&maxValue=" + maxValue));
        req.setAttribute(PAGE, bookPage);
        req.setAttribute("minValue", minValue);
        req.setAttribute("maxValue", maxValue);
        req.getRequestDispatcher(CLIENT_INDEX_JSP).forward(req, resp);
    }
}
