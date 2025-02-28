package com.jira.requestModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jira.enums.Role;
import com.jira.enums.Status;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    // Regex patterns
    public static final String NAME_REGEX = "^[a-zA-Z]{3,20}$";
    public static final String SURNAME_REGEX = "^[a-zA-Z]{3,20}$";
    public static final String EMAIL_REGEX = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,6}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";

    // Validation messages
    public static final String NAME_NULL_MESSAGE = "Name cannot be null";
    public static final String NAME_INVALID_MESSAGE = "Name must be between 3 and 20 alphabetic characters";
    public static final String SURNAME_NULL_MESSAGE = "Surname cannot be null";
    public static final String SURNAME_INVALID_MESSAGE = "Surname must be between 3 and 20 alphabetic characters";
    public static final String EMAIL_NULL_MESSAGE = "Email cannot be null";
    public static final String EMAIL_INVALID_MESSAGE = "Email must be a valid format";
    public static final String PASSWORD_NULL_MESSAGE = "Password cannot be null";
    public static final String PASSWORD_INVALID_MESSAGE = "Password must be at least 8 characters long, with one uppercase letter, one lowercase letter, one digit, and one special character";

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = NAME_NULL_MESSAGE)
    @Pattern(regexp = NAME_REGEX, message = NAME_INVALID_MESSAGE)
    @Schema(example = "jon", description = "The name of the user")
    private String name;

    @NotNull(message = SURNAME_NULL_MESSAGE)
    @Pattern(regexp = SURNAME_REGEX, message = SURNAME_INVALID_MESSAGE)
    @Schema(example = "Doe", description = "The surname of the user")
    private String surname;

    @NotNull(message = EMAIL_NULL_MESSAGE)
    @Pattern(regexp = EMAIL_REGEX, message = EMAIL_INVALID_MESSAGE)
    @Schema(example = "jon@gmail.com", description = "The email of the user")
    private String email;

    @NotNull(message = PASSWORD_NULL_MESSAGE)
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_INVALID_MESSAGE)
    @Schema(example = "p@ssW0rd", description = "The password of the user")
    private String password;

    @Schema(hidden = true)
    private Role role;

    @Schema(hidden = true)
    private Status status;

    @Schema(hidden = true)
    private String verifyCode;

    @Schema(hidden = true)
    private String resetToken;
}
