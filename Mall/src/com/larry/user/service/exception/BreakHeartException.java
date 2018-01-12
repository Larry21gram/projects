package com.larry.user.service.exception;

public class BreakHeartException extends UserException {
    @Override
    public String getMessage() {
        return super.getMessage()+"滚开！你这让我心碎的男人！";
    }
}
