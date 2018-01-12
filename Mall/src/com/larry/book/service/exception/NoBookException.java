package com.larry.book.service.exception;

public class NoBookException extends BookException {
    @Override
    public String getMessage() {
        return super.getMessage() + "查找所有书籍失败";
    }
}
