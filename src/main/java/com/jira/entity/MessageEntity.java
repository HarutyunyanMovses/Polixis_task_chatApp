package com.jira.entity;

import com.jira.requestModels.MessageRequest;
import com.jira.utils.constants.DataBaseConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = DataBaseConstants.SCHEMA_NAME, name = DataBaseConstants.MESSAGES_TABLE_NAME)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID sender;

    @Column(nullable = false)
    private UUID recipient; // Can be a user ID, group ID, or "ALL"

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant timestamp;

    public MessageEntity(MessageRequest messageRequest) {
        this.id = (messageRequest.getId() != null) ? messageRequest.getId() : UUID.randomUUID();
        this.sender = messageRequest.getSender();
        this.recipient = messageRequest.getRecipient();
        this.content = messageRequest.getContent();
        this.timestamp = (messageRequest.getTimestamp() != null) ? messageRequest.getTimestamp() : Instant.now();
    }

    public MessageRequest toMessageRequest() {
        return new MessageRequest(id, sender, recipient, content, timestamp);
    }
}
