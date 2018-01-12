package com.larry.cart.service;

import com.larry.book.dao.BookDao;
import com.larry.book.domain.Book;
import com.larry.book.service.exception.NoThatBookException;
import com.larry.cart.dao.CartDao;
import com.larry.cart.domain.CartItem;
import com.larry.cart.service.exception.NoMapException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    private CartDao cartDao = new CartDao();
    private BookDao bookDao = new BookDao();
    public Book getBooksByBids(String bid) throws NoThatBookException {
        return bookDao.findBookByBid(bid);
    }

    public List<CartItem> getCartItemList(Map<String, CartItem> map) {
        List<CartItem> list = new ArrayList<>();
        for (CartItem item : map.values()) {
            list.add(item);
        }
        return list;
    }

    public Map<String, CartItem> deleteByBid(String bid, Map<String,CartItem> map) {
        Map<String,CartItem> mapCopy = new HashMap<>();
        for (Map.Entry<String, CartItem> entry : map.entrySet()) {
            if (!entry.getValue().getBook().getBid().equals(bid)){
                mapCopy.put(entry.getKey(),entry.getValue());
            }
        }
        return mapCopy;
    }
}
