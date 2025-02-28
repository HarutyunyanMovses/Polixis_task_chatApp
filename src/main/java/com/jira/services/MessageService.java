package com.jira.services;

import com.jira.entity.MessageEntity;
import com.jira.repository.MessageRepository;
import com.jira.requestModels.MessageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class MessageService {

    @Inject
    MessageRepository messageRepository;

//    @Inject
//    @Channel("chat-messages")
//    Emitter<MessageRequest> messageEmitter;

    @Transactional
    public MessageRequest sendMessage(MessageRequest messageRequest) {
        messageRepository.persist(new MessageEntity(messageRequest));
        return messageRequest;
    }

    public List<MessageRequest> getAllMessages() {
        return messageRepository.listAll().stream()
                .map(msg -> msg.toMessageRequest())
                .collect(Collectors.toList());
    }

    public Optional<MessageRequest> getMessageById(UUID messageId) {
        return messageRepository.findByMessageId(messageId)
                .map(msg -> msg.toMessageRequest());
    }

    public List<MessageRequest> getMessagesByUser(UUID userId) {
        return messageRepository.findBySender(userId).stream()
                .map(msg -> msg.toMessageRequest())
                .collect(Collectors.toList());
    }
}
