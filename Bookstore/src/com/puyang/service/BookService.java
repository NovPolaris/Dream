package com.puyang.service;

import com.puyang.types.Book;
import com.puyang.types.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> queryBooksInCurrentPage(int pageNumber, int pageSize);

    Page<Book> queryBooksInCurrentPageByPrice(int pageNumber, int pageSize, int minValue, int maxValue);
}
