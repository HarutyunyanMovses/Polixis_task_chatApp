package com.jira.repository;

import com.jira.entity.MessageEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MessageRepository implements PanacheRepository<MessageEntity> {

    public List<MessageEntity> findBySender(UUID senderId) {
        return list("sender", senderId);
    }

    public List<MessageEntity> findByRecipient(UUID recipientId) {
        return list("recipient", recipientId);
    }
    public Optional<MessageEntity> findByMessageId(UUID id) {
        return find("message_id", id).firstResultOptional();
    }
}
