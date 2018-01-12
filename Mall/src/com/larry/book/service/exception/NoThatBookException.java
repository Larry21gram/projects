package com.larry.book.service.exception;

public class NoThatBookException extends BookException{
    @Override
    public String getMessage() {
        return super.getMessage() + "没有这本书:";
    }
}
