package com.shankulk.exception;

public class AccountDoesntExistException extends Exception {

    public AccountDoesntExistException(String message) {
        super(message);
    }
}
