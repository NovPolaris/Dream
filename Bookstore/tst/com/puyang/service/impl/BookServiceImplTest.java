package com.puyang.service.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.impl.BookDaoImpl;
import com.puyang.pojo.Book;
import com.puyang.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BookServiceImplTest {
    public static final String SQL_SELECT_BY_NAME = "select * from t_book where name = ?";
    public static final String SQL_DELETE_BY_NAME = "delete from t_book where name = ?";
    public static final String NAME = "unit_test";
    private static final Book BOOK = Book.builder()
            .name(NAME)
            .author("author")
            .price(10.0)
            .sales(1)
            .stock(5)
            .build();

    private BaseDao baseDao;
    private BookService bookService;

    @BeforeEach
    public void before() {
        baseDao = new BookDaoImpl();
        bookService = new BookServiceImpl();
        bookService.addBook(BOOK);
    }

    @AfterEach
    public void after() {
        baseDao.update(SQL_DELETE_BY_NAME, NAME);
    }

    @Test
    public void addBook() {
        Book actual = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        assertEquals(BOOK, actual);
    }

    @Test
    public void deleteBookById() {
        Book actual = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        bookService.deleteBookById(actual.getId());
        assertNull(bookService.queryBookById(actual.getId()));
    }

    @Test
    public void updateBook() {
        Book originalBook = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        Book updatedBook = Book.builder()
                .name("unit_test")
                .author("author")
                .price(20.0)
                .sales(4)
                .stock(2)
                .id(originalBook.getId())
                .build();
        bookService.updateBook(updatedBook);
        Book actual = bookService.queryBookById(originalBook.getId());

        assertEquals(updatedBook, actual);
    }

    @Test
    public void queryBookById() {
        Book expected = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        Book actual = bookService.queryBookById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookService.queryBooks();
        assertTrue(bookList.size() > 0);
        assertTrue(bookList.contains(BOOK));
    }
}
