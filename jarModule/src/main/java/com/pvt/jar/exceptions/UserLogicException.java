package com.pvt.jar.exceptions;

public class UserLogicException extends LogicException{
    public UserLogicException(){

        super();
    }

    public UserLogicException(String message){

        super(message);
    }

    public UserLogicException(String message, Exception e){

        super(message,e);
    }

    public UserLogicException(Exception e){

        super(e);
    }
}
