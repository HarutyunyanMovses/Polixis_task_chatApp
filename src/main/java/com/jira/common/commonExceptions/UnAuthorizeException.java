package com.jira.common.commonExceptions;

public  class UnAuthorizeException extends RuntimeException {
    public UnAuthorizeException(String message) {
        super(message);
    }
}
