package com.larry.cart.domain;

import com.larry.book.domain.Book;

/**
 * 用于保存书本及其数量
 */
public class CartItem {
    private Book book;
    private int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartItem() {

    }
}
