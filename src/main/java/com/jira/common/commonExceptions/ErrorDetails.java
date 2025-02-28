package com.jira.common.commonExceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields
@RegisterForReflection // Quarkus Native Image support
public class ErrorDetails {

    @JsonProperty("message")
    private String message;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("details")
    private List<String> details;

    public ErrorDetails(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
