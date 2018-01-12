package com.larry.user.service.exception;

public class ChangeFailedException extends UserException {
    @Override
    public String getMessage() {
        return super.getMessage()+"激活失败！请重新注册！";
    }
}
