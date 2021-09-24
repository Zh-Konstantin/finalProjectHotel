package com.example.exceptions;

/**
 * Custom checked exception type which is to be thrown
 * after unsuccessful SQL database request
 */
public class UnsuccessfulRequestException extends RuntimeException{

    public UnsuccessfulRequestException() {
        super("Unsuccessful SQL request");
    }

    public UnsuccessfulRequestException(String message) {
        super(message);
    }

    public UnsuccessfulRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
