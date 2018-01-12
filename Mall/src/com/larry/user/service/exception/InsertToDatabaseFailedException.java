package com.larry.user.service.exception;

public class InsertToDatabaseFailedException extends UserException {
    @Override
    public String getMessage() {
        return super.getMessage()+"用户未存入数据库";
    }
}
