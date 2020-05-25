package com.termos.scooterrental.exception;

public class UserAddException extends Exception {
    public UserAddException(String msg) {
        super(msg);
    }


    public static UserAddException incorrectEmailAdress(String email) {
        return new UserAddException(String.format("Incorrect e-mail address %s", email));
    }

    public static UserAddException incorrectAge(Integer age) {
        return new UserAddException(String.format("Incorrect age, try 1-100 %s", age));
    }


}
