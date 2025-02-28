package com.jira.requestModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @Schema(example = "hsrutyunyanmovses@gmail.com", description = "The email of the user")
    private String email;

    @Schema(example = "p@ssW0rd", description = "The password of the user")
    private String password;
}
