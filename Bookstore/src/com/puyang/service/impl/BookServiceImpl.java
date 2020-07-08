package com.puyang.service.impl;

import com.puyang.dao.BookDao;
import com.puyang.dao.impl.BookDaoImpl;
import com.puyang.pojo.Book;
import com.puyang.pojo.Page;
import com.puyang.service.BookService;

import java.util.List;

public class  BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> queryBooksInCurrentPage(int pageNumber, int pageSize) {
        return bookDao.queryItemsInCurrentPage(pageNumber, pageSize);
    }

    @Override
    public Page<Book> queryBooksInCurrentPageByPrice(int pageNumber, int pageSize, int minValue, int maxValue) {
        return bookDao.queryItemsInCurrentPageByPrice(pageNumber, pageSize, minValue, maxValue);
    }
}
