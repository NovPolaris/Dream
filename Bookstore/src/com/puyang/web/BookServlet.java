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
import java.util.List;

public class BookServlet extends BaseServlet {
    public static final String SERVLET_URI = "manager/bookServlet?action=page";
    public static final String BOOK_MANAGER_JSP = "/pages/manager/book_manager.jsp";
    public static final String BOOK_EDIT_JSP = "/pages/manager/book_edit.jsp";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_LIST = "/manager/bookServlet?action=page&pageNumber=";
    public static final String BOOK = "book";
    public static final String BOOKS = "books";
    public static final String PAGE = "page";
    public static final String ID = "id";

    private final BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamsToBean(new Book(), req.getParameterMap());
        bookService.addBook(book);
        int page = Integer.parseInt(req.getParameter(PAGE_NUMBER)) + 1;
        resp.sendRedirect(req.getContextPath() + PAGE_LIST + page);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        bookService.deleteBookById(Integer.parseInt(req.getParameter(ID)));
        resp.sendRedirect(req.getContextPath() + PAGE_LIST + Integer.parseInt(req.getParameter(PAGE_NUMBER)));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamsToBean(new Book(), req.getParameterMap());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + PAGE_LIST + Integer.parseInt(req.getParameter(PAGE_NUMBER)));
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = bookService.queryBookById(Integer.parseInt(req.getParameter(ID)));
        req.setAttribute(BOOK, book);
        req.getRequestDispatcher(BOOK_EDIT_JSP).forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute(BOOKS, books);
        req.getRequestDispatcher(BOOK_MANAGER_JSP).forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        int pageNumber = WebUtils.parseInt(req.getParameter(PAGE_NUMBER), 1);
        int pageSize = WebUtils.parseInt(req.getParameter(PAGE_SIZE), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.queryBooksInCurrentPage(pageNumber, pageSize);
        bookPage.setUri(new URI(SERVLET_URI));
        req.setAttribute(PAGE, bookPage);
        req.getRequestDispatcher(BOOK_MANAGER_JSP).forward(req, resp);
    }
}
