package com.jira.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}

