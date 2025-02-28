package com.jira.requestModels;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageRequest {
    @Hidden
    private UUID id;
    private UUID sender;
    private UUID recipient;
    private String content;
    @Hidden
    private  Instant timestamp;
}
