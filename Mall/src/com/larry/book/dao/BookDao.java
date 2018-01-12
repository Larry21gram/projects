package com.larry.book.dao;

import com.larry.book.domain.Book;
import com.larry.book.service.exception.NoThatBookException;
import com.larry.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private Connection connection;
    private QueryRunner qr = new QueryRunner();

    public List<Book> getAllBook() {
        String sql = "SELECT * from book";
        connection = C3p0Utils.getConn();
        List<Book> books = null;
        try {
             books = qr.query(connection, sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBooksByKey(String key) {
        String sql = "SELECT * FROM book WHERE cid=(SELECT cid from category WHERE cname=?)";
        connection = C3p0Utils.getConn();
        List<Book> books = null;
        try {
            books = qr.query(connection, sql, new BeanListHandler<Book>(Book.class), key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findBookByBid(String bid) throws NoThatBookException {
        String sql = "SELECT * FROM book WHERE bid=?";
        connection = C3p0Utils.getConn();
        try {
            Book book = qr.query(connection, sql, new BeanHandler<Book>(Book.class),bid);
            return book;
        } catch (SQLException e) {

            try {
                connection.close();
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
            throw new NoThatBookException();
        }


    }
}
