package com.larry.user.service.exception;

public class UserAlreadyExistedException extends UserException {
    @Override
    public String getMessage() {
        return super.getMessage()+"用户名已存在";
    }
}
