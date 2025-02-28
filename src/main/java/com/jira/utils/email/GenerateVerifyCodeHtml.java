package com.jira.utils.email;

public class GenerateVerifyCodeHtml {

    public static String generateVerifyCodeHtml(String code) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Chat App - Email Verification</title>\n" +
                "</head>\n" +
                "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;\">\n" +
                "    <table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\">\n" +
                "        <tr>\n" +
                "            <td style=\"background-color: #2D89EF; padding: 20px; text-align: center; color: #ffffff; border-top-left-radius: 8px; border-top-right-radius: 8px;\">\n" +
                "                <h1 style=\"margin: 0; font-size: 24px;\">Chat Application</h1>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td style=\"padding: 20px; color: #333333;\">\n" +
                "                <p style=\"font-size: 18px; margin: 0;\">Hello,</p>\n" +
                "                <p style=\"font-size: 16px; line-height: 1.5; margin: 10px 0;\">\n" +
                "                    Thank you for signing up for our Chat Application! Use the verification code below to activate your account and start chatting.\n" +
                "                </p>\n" +
                "                <div style=\"text-align: center; margin: 20px 0;\">\n" +
                "                    <span style=\"display: inline-block; font-size: 24px; font-weight: bold; color: #2D89EF; background-color: #E0F0FF; padding: 10px 20px; border-radius: 5px;\">\n" +
                "                        " + code + "\n" +
                "                    </span>\n" +
                "                </div>\n" +
                "                <p style=\"font-size: 16px; line-height: 1.5; margin: 10px 0;\">\n" +
                "                    This code is valid for **15 minutes**. If you did not request this email, you can ignore it.\n" +
                "                </p>\n" +
                "                <p style=\"font-size: 16px; margin: 20px 0 0;\">Best regards,<br>The Chat Application Team</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td style=\"background-color: #f4f4f4; padding: 10px; text-align: center; font-size: 12px; color: #777777; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;\">\n" +
                "                © 2025 Chat Application. All rights reserved.\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";
    }
}

