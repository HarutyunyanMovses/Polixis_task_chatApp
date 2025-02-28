package com.jira.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.RandomStringUtils;

public class UserCodeGenerator {
    public static String verifyCodeGenerator() {
        return RandomStringUtils.random(6,true,true);
    }
    public static String resetTokenGenerator() {
        return RandomStringUtils.random(6,false,true);
    }

}