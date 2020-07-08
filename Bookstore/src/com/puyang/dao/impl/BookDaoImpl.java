package com.puyang.dao.impl;

import com.google.common.primitives.Ints;
import com.puyang.dao.BaseDao;
import com.puyang.dao.BookDao;
import com.puyang.pojo.Book;
import com.puyang.pojo.Page;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name, author, price, sales, stock, img_path) values (?, ?, ?, ?, ?, ?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?, author = ?, price = ?, sales = ?, stock = ?, img_path = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public long queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        return (Long) queryForSingleValue(sql);
    }

    @Override
    public long queryForPageTotalCountByPrice(int minValue, int maxValue) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return (Long) queryForSingleValue(sql, minValue, maxValue);
    }

    @Override
    public Page<Book> queryItemsInCurrentPage(int pageNumber, int pageSize) {
        String sql = "select * from t_book limit ?, ?";
        long count = queryForPageTotalCount();
        int pageTotal = (int) (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        int curtPage = Ints.constrainToRange(pageNumber, 1, pageTotal);
        List<Book> books = queryForList(Book.class, sql, (curtPage - 1) * pageSize, pageSize);
        return Page.<Book>builder()
                .pageTotal(pageTotal)
                .pageSize(pageSize)
                .pageNumber(curtPage)
                .count(count)
                .items(books)
                .build();
    }

    @Override
    public Page<Book> queryItemsInCurrentPageByPrice(int pageNumber, int pageSize, int minValue, int maxValue) {
        String sql = "select * from t_book where price between ? and ? limit ?, ?";
        long count = queryForPageTotalCountByPrice(minValue, maxValue);
        int pageTotal = (int) (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        int curtPage = Ints.constrainToRange(pageNumber, 1, pageTotal);
        List<Book> books = queryForList(Book.class, sql, minValue, maxValue, (curtPage - 1) * pageSize, pageSize);
        return Page.<Book>builder()
                .pageTotal(pageTotal)
                .pageNumber(curtPage)
                .pageSize(pageSize)
                .count(count)
                .items(books)
                .build();
    }
}
