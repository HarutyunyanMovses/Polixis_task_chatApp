package com.jira.services;

import com.jira.common.userExceptions.UserBadRequestException;
import com.jira.entity.UserEntity;
import com.jira.repository.UserRepository;
import com.jira.requestModels.LoginRequest;
import com.jira.utils.PasswordEncoder;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;



    public Response login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserBadRequestException("Invalid email or password"));

        if (!PasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginRequest(null, "Invalid email or password"))
                    .build();
        }

        String token = generateToken(user);

        return Response.ok(new LoginRequest(token, "Login successful")).build();
    }

    private String generateToken(UserEntity user) {
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().name());

        return Jwt.issuer("http://localhost:8080")
                .subject(user.getId().toString())
                .upn(user.getEmail())
                .claim("status", user.getStatus().name())
                .claim("groups", roles)
                .expiresIn(Duration.ofMinutes(20))
                .sign();
    }
}
