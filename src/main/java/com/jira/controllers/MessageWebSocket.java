//package com.jira.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jira.requestModels.MessageRequest;
//import io.quarkus.security.Authenticated;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.websocket.*;
//import jakarta.websocket.server.PathParam;
//import jakarta.websocket.server.ServerEndpoint;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//import java.io.IOException;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//@ApplicationScoped
//@Authenticated
//@ServerEndpoint("/ws/chat/{userId}") // WebSocket Endpoint
//public class MessageWebSocket {
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//    private static final Map<UUID, Session> sessions = new ConcurrentHashMap<>();
//
//    @Inject
//    @Channel("chat-messages-out")
//    Emitter<MessageRequest> messageEmitter;
//
//    @OnOpen
//    public void onOpen(@PathParam("userId") String userId, Session session) {
//        try {
//            UUID uuid = UUID.fromString(userId);
//            sessions.put(uuid, session);
//            System.out.println("User connected: " + uuid);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid UUID format: " + userId);
//            try {
//                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Invalid userId"));
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String messageJson, @PathParam("userId") String userId) {
//        try {
//            UUID uuid = UUID.fromString(userId);
//            MessageRequest message = parseJson(messageJson);
//            messageEmitter.send(message); // Send message to Kafka
//
//            // Forward message if recipient is online
//            Session recipientSession = sessions.get(message.getRecipient());
//            if (recipientSession != null) {
//                recipientSession.getBasicRemote().sendText(messageJson);
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid UUID format: " + userId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnClose
//    public void onClose(@PathParam("userId") String userId) {
//        try {
//            UUID uuid = UUID.fromString(userId);
//            sessions.remove(uuid);
//            System.out.println("User disconnected: " + uuid);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid UUID format: " + userId);
//        }
//    }
//
//    @OnError
//    public void onError(Throwable throwable) {
//        throwable.printStackTrace();
//    }
//
//    public static void broadcastMessage(MessageRequest message) {
//        try {
//            Session recipientSession = sessions.get(message.getRecipient());
//            if (recipientSession != null) {
//                recipientSession.getBasicRemote().sendText(serializeMessage(message));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String serializeMessage(MessageRequest message) {
//        try {
//            return objectMapper.writeValueAsString(message);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "{}";
//        }
//    }
//
//    private MessageRequest parseJson(String json) {
//        try {
//            return objectMapper.readValue(json, MessageRequest.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
