package com.pvt.jar.exceptions;

public class PostLogicException extends LogicException{
    public PostLogicException() {
    }

    public PostLogicException(String message) {
        super(message);
    }

    public PostLogicException(String message, Exception e) {
        super(message, e);
    }

    public PostLogicException(Exception e) {
        super(e);
    }
}
