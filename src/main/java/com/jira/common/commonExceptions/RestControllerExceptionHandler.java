package com.jira.common.commonExceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class RestControllerExceptionHandler {

    @Provider
    public static class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {
        @Override
        public Response toResponse(BadRequestException exception) {
            ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class ApiExceptionHandler implements ExceptionMapper<ApiException> {
        @Override
        public Response toResponse(ApiException exception) {
            ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class ResourceAlreadyExistExceptionHandler implements ExceptionMapper<ResourceAlreadyExistException> {
        @Override
        public Response toResponse(ResourceAlreadyExistException exception) {
            ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), Response.Status.CONFLICT.getStatusCode());
            return Response.status(Response.Status.CONFLICT)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {
        @Override
        public Response toResponse(EntityNotFoundException exception) {
            ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), Response.Status.NOT_FOUND.getStatusCode());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class UnAuthorizeExceptionHandler implements ExceptionMapper<UnAuthorizeException> {
        @Override
        public Response toResponse(UnAuthorizeException exception) {
            ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), Response.Status.UNAUTHORIZED.getStatusCode());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
        @Override
        public Response toResponse(ConstraintViolationException exception) {
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            List<String> details = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            ErrorDetails errorDetails = new ErrorDetails("Invalid request", Response.Status.BAD_REQUEST.getStatusCode(), details);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorDetails)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
