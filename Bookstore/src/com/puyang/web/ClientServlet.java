package com.puyang.web;

import com.puyang.pojo.Book;
import com.puyang.pojo.Page;
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

    private final BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        int pageNumber = WebUtils.parseInt(req.getParameter(PAGE_NUMBER), 1);
        int pageSize = WebUtils.parseInt(req.getParameter(PAGE_SIZE), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.queryBooksInCurrentPage(pageNumber, pageSize);
        bookPage.setUri(new URI(SERVLET_URI));
        System.out.println();
        req.setAttribute(PAGE, bookPage);
        req.getRequestDispatcher(CLIENT_INDEX_JSP).forward(req, resp);
    }
}
