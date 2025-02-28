package com.jira.common.userExceptions;

import com.jira.common.commonExceptions.BadRequestException;

public class UserBadRequestException extends BadRequestException {
    public UserBadRequestException(String message) {
        super(message);
    }
}
