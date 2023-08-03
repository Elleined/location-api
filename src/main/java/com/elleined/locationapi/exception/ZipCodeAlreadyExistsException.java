package com.elleined.locationapi.exception;

public class ZipCodeAlreadyExistsException extends RuntimeException {
    public ZipCodeAlreadyExistsException(String message) {
        super(message);
    }
}
