package com.larry.user.service.exception;

public class UserException extends Exception {
    @Override
    public String getMessage() {
        return "错误：";
    }
}
