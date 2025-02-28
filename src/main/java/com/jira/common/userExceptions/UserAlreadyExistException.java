package com.jira.common.userExceptions;

import com.jira.common.commonExceptions.ResourceAlreadyExistException;

public class UserAlreadyExistException extends ResourceAlreadyExistException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
