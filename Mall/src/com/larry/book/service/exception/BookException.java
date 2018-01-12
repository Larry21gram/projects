package com.larry.book.service.exception;

public class BookException extends Exception{

    @Override
    public String getMessage() {
        return "有关书籍的错误：";
    }
}
