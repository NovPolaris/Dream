package com.puyang.dao.impl;

import com.puyang.dao.BaseDao;
import com.puyang.dao.BookDao;
import com.puyang.pojo.Book;
import com.puyang.utils.JdbcUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BookDaoImplTest {
    public static final String SQL_SELECT_BY_NAME = "select * from t_book where name = ?";
    public static final String SQL_DELETE_BY_NAME = "delete from t_book where name = ?";
    public static final String NAME = "unit_test";
    public static final Book BOOK = Book.builder()
            .name(NAME)
            .author("author")
            .price(10.0)
            .sales(1)
            .stock(5)
            .build();

    private BaseDao baseDao;
    private BookDao bookDao;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        baseDao = new BookDaoImpl();
        bookDao = new BookDaoImpl();
        bookDao.addBook(BOOK);
        connection = JdbcUtils.getConnection();
        statement = null;
        resultSet = null;
    }

    @AfterEach
    public void tearDown() {
        baseDao.update(SQL_DELETE_BY_NAME, NAME);
        JdbcUtils.close(connection, statement, resultSet);
    }

    @Test
    public void addBook() {
        Book actual = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        assertEquals(BOOK, actual);
    }

    @Test
    public void deleteBookById() {
        Book actual = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        bookDao.deleteBookById(actual.getId());
        assertNull(bookDao.queryBookById(actual.getId()));
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
        bookDao.updateBook(updatedBook);
        Book actual = bookDao.queryBookById(originalBook.getId());

        assertEquals(updatedBook, actual);
    }

    @Test
    public void queryBookById() {
        Book expected = baseDao.queryForOne(Book.class, SQL_SELECT_BY_NAME, NAME);
        Book actual = bookDao.queryBookById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookDao.queryBooks();
        assertTrue(bookList.size() > 0);
        assertTrue(bookList.contains(BOOK));
    }

    @Test
    void queryForPageTotalCount() {
        int count = 0;
        String sql = "SELECT count(*) AS count FROM t_book";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertEquals(count, bookDao.queryForPageTotalCount());
    }

    @Test
    void queryForItems() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM t_book LIMIT 0, 4";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                books.add(Book.builder()
                        .id(Integer.valueOf(resultSet.getString("id")))
                        .stock(Integer.valueOf(resultSet.getString("stock")))
                        .sales(Integer.valueOf(resultSet.getString("sales")))
                        .author(resultSet.getString("author"))
                        .name(resultSet.getString("name"))
                        .price(Double.valueOf(resultSet.getString("price")))
                        .imgPath(resultSet.getString("img_path"))
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertEquals(books, bookDao.queryItemsInCurrentPage(1, 4).getItems());
    }
} 
