package com.jira.controllers;

import com.jira.entity.MessageEntity;
import com.jira.requestModels.MessageRequest;
import com.jira.services.MessageService;
import com.jira.utils.constants.RoutConstants;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path(RoutConstants.BASE_URL + RoutConstants.MESSAGE_SERVICE_VERSION + RoutConstants.MESSAGE_SERVICE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class MessageController {

    @Inject
    MessageService messageService;

    @POST
    public Response sendMessage(MessageRequest messageRequest) {
        MessageRequest message = messageService.sendMessage(messageRequest);
        return Response.status(Response.Status.CREATED).entity(message).build();
    }

    @GET
    public Response getAllMessages() {
        List<MessageRequest> messages = messageService.getAllMessages();
        return Response.ok(messages).build();
    }

    @GET
    @Path("/{messageId}")
    public Response getMessageById(@PathParam("messageId") UUID messageId) {
        Optional<MessageRequest> messageRequest = messageService.getMessageById(messageId);
        if (messageRequest.isPresent()) {
            return Response.ok(messageRequest).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Message not found").build();
        }
    }

    @GET
    @Path("/{userId}")
    public Response getMessagesByUser(@PathParam("userId") UUID userId) {
        List<MessageRequest> messages = messageService.getMessagesByUser(userId);
        return Response.ok(messages).build();
    }
}
