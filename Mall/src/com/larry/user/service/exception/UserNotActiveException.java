package com.larry.user.service.exception;

public class UserNotActiveException extends UserException{
    @Override
    public String getMessage() {
        return super.getMessage()+"用户未激活！";
    }
}
