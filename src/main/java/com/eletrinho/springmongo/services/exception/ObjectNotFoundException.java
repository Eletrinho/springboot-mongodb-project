package com.eletrinho.springmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String id) {
        super("Resource not found. ID: " + id);
    }
}
