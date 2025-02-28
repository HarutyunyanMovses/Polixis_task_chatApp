package com.jira.controllers;

import com.jira.requestModels.LoginRequest;
import com.jira.services.AuthService;
import com.jira.utils.constants.RoutConstants;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(RoutConstants.BASE_URL + RoutConstants.AUTHENTICATION_URL)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest request) {
        return authService.login(request);
    }
}
