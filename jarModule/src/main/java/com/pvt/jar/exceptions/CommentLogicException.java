package com.pvt.jar.exceptions;

public class CommentLogicException extends LogicException{
    public CommentLogicException() {
    }

    public CommentLogicException(String message) {
        super(message);
    }

    public CommentLogicException(String message, Exception e) {
        super(message, e);
    }

    public CommentLogicException(Exception e) {
        super(e);
    }
}
