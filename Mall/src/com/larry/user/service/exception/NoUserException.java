package com.larry.user.service.exception;

public class NoUserException extends UserException{
    @Override
    public String getMessage() {
        return super.getMessage()+"用户名或密码错误，么么哒！！！";
    }
}
