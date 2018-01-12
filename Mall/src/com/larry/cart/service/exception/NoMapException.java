package com.larry.cart.service.exception;

import com.larry.book.service.exception.BookException;

public class NoMapException extends BookException {
    @Override
    public String getMessage() {
        return super.getMessage() + "购物车为空！";
    }
}
