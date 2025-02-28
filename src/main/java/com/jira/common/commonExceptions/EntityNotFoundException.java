package com.jira.common.commonExceptions;

public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
