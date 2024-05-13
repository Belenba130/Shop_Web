package com.example.shop_web.exception;

public class BaseException extends RuntimeException {
    private Error errorMessage;
    private ErrorMessages errorMessages = new ErrorMessages();

    public BaseException(String errorCode) {
        super(errorCode);
        errorMessage = new Error();
        errorMessage.setCodeE(errorCode);
        errorMessage.setMassage(errorMessages.getMessage(errorCode));
    }

    public Error getErrorMessage() {
        return this.errorMessage;
    }
}
