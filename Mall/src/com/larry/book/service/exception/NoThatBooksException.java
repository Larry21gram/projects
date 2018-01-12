package com.larry.book.service.exception;

public class NoThatBooksException extends BookException {
    private String key;
    public NoThatBooksException(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "未查到该类型书籍，类型为"+ this.key ;
    }
}
