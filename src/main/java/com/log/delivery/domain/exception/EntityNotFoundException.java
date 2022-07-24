package com.log.delivery.domain.exception;

public class EntityNotFoundException extends DomainExceptionHandler {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
