package com.jira.common.userExceptions;


import com.jira.common.commonExceptions.ApiException;

public class UserApiException extends ApiException {
    public UserApiException(String message) {
        super(message);
    }

    public UserApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
