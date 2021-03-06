package com.puyang.dao;

import com.puyang.types.Book;
import com.puyang.types.Page;

import java.util.List;

public interface BookDao {
    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    long queryForPageTotalCount();

    long queryForPageTotalCountByPrice(int minValue, int maxValue);

    Page<Book> queryItemsInCurrentPage(int pageNumber, int pageSize);

    Page<Book> queryItemsInCurrentPageByPrice(int pageNumber, int pageSize, int minValue, int maxValue);
}
