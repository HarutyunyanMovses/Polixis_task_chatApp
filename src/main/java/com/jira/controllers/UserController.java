package com.jira.controllers;

import com.jira.requestModels.UserRequest;
import com.jira.services.UserService;
import com.jira.utils.constants.RoutConstants;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path(RoutConstants.BASE_URL + RoutConstants.USER_SERVICE_VERSION + RoutConstants.USER_SERVICE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    // 🛠️ Public Endpoint (Anyone can create an account)
    @POST
    @Path("/register")
    public Response createUser(UserRequest userRequest) {
        UserRequest createdUser = userService.createUser(userRequest);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    // 🔒 Only Admins Can View All Users
    @GET
    @RolesAllowed("ADMIN")
    public Response getAllUsers() {
        List<UserRequest> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    // 🔒 User Can View Their Own Data or Admins Can View Any User
    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public Response getUser(@PathParam("id") UUID id) {
        UserRequest user = userService.getUser(id);
        return Response.ok(user).build();
    }

    // 🔒 Only the User Themselves or an Admin Can Update User Info
    @PUT
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public Response updateUser(@PathParam("id") UUID id, UserRequest userRequest) {
        UserRequest updatedUser = userService.updateUser(id, userRequest);
        return Response.ok(updatedUser).build();
    }

    // 🔒 Only Admins Can Delete Users
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteUser(@PathParam("id") UUID id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    // 🛠️ Public Endpoint for Email Verification
    @POST
    @Path("/verify")
    public Response verifyUser(@QueryParam("email") String email, @QueryParam("code") String verificationCode) {
        UserRequest verifiedUser = userService.verifyUser(email, verificationCode);
        return Response.ok(verifiedUser).build();
    }
}
