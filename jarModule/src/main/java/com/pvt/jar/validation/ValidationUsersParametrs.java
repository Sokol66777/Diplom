package com.pvt.jar.validation;

import com.pvt.jar.exceptions.UserLogicException;

public class ValidationUsersParametrs {

    public static void validationPassword(String password) throws UserLogicException {
        if(!password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            throw new UserLogicException("Password is not valid");
        }
    }
}
