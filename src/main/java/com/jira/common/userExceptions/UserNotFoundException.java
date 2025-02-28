package com.jira.common.userExceptions;


import com.jira.common.commonExceptions.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
