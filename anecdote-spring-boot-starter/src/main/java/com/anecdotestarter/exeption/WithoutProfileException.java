package com.anecdotestarter.exeption;

public class WithoutProfileException extends RuntimeException{

    public WithoutProfileException(String message) {
        super(message);
    }
}
