package com.log.delivery.domain.exception;

public class DomainExceptionHandler extends RuntimeException {

    public DomainExceptionHandler(String message) {
        super(message);
    }    
}
