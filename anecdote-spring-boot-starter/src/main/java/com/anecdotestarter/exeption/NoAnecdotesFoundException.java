package com.anecdotestarter.exeption;

public class NoAnecdotesFoundException extends RuntimeException {

    public NoAnecdotesFoundException(String message) {
        super(message);
    }
}
