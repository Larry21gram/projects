package com.larry.user.service.exception;

public class NotEmailFormatException extends UserException {
    @Override
    public String getMessage() {
        return super.getMessage()+"邮箱格式不正确";
    }
}
