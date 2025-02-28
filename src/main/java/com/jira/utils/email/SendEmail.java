package com.jira.utils.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SendEmail {

    @Inject
    Mailer mailer;

    public void send(String to, String subject, String code) {
        try {
            mailer.send(
                    Mail.withHtml(to, subject, GenerateVerifyCodeHtml.generateVerifyCodeHtml(code))
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
