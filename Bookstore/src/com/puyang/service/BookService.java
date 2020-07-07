package com.puyang.service;

import com.puyang.pojo.Book;
import com.puyang.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> queryBooksInCurrentPage(int pageNumber, int pageSize);
}
