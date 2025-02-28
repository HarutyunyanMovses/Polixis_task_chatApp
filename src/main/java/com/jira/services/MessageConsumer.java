//package com.jira.services;
//
//import com.jira.controllers.MessageWebSocket;
//import com.jira.entity.MessageEntity;
//import com.jira.repository.MessageRepository;
//import com.jira.requestModels.MessageRequest;
//import io.smallrye.reactive.messaging.annotations.Blocking;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//
//@ApplicationScoped
//public class MessageConsumer {
//
//    @Inject
//    MessageRepository messageRepository;
//
//
//    @Blocking
//    @Transactional
//    @Incoming("chat-messages-in")
//    public void consumeMessage(MessageRequest message) {
//        System.out.println("Received message: " + message);
//        MessageWebSocket.broadcastMessage(message);
//    }
//}
