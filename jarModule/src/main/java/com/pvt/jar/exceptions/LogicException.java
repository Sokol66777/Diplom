package com.pvt.jar.exceptions;

public class LogicException extends Exception{
    public LogicException() {

        super();
    }

    public LogicException(String message) {

        super(message);
    }

    public LogicException(String message, Exception e) {

        super(message, e);
    }

    public LogicException(Exception e) {

        super(e);
    }
}
