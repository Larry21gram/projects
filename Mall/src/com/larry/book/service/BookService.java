package com.larry.book.service;

import com.larry.book.dao.BookDao;
import com.larry.book.domain.Book;
import com.larry.book.service.exception.NoBookException;
import com.larry.book.service.exception.NoThatBookException;
import com.larry.book.service.exception.NoThatBooksException;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    // TODO: 2018/1/11 查询所有书籍，并返回书籍
    public List<Book> getAllBook() throws NoBookException {
        List<Book> allBook = bookDao.getAllBook();
        if (allBook != null){
            return allBook;
        }
        throw new NoBookException();
    }

    public List<Book> getBookByKey(String key) throws NoThatBooksException {
        List<Book> booksByKey = bookDao.getBooksByKey(key);
        if (booksByKey!=null){
            return booksByKey;
        }
        throw new NoThatBooksException(key);
    }

    public Book fidBook(String bid) throws NoThatBookException {
        Book bookByBid = bookDao.findBookByBid(bid);
        return bookByBid;
    }
}
