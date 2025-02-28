package com.jira.services;

import com.jira.common.userExceptions.UserAlreadyExistException;
import com.jira.common.userExceptions.UserApiException;
import com.jira.common.userExceptions.UserBadRequestException;
import com.jira.common.userExceptions.UserNotFoundException;
import com.jira.entity.UserEntity;
import com.jira.enums.Role;
import com.jira.enums.Status;
import com.jira.repository.UserRepository;
import com.jira.requestModels.UserRequest;
import com.jira.utils.PasswordEncoder;
import com.jira.utils.UserCodeGenerator;
import com.jira.utils.email.SendEmail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private SendEmail sendEmail;

    // Method to create a new user
    @Transactional
    public UserRequest createUser(UserRequest userRequest) {
        validateCreateUserRequest(userRequest);

        // Create UserEntity from UserRequest
        UserEntity userEntity = new UserEntity(userRequest);
        userEntity.setVerifyCode( UserCodeGenerator.verifyCodeGenerator());
        userEntity.setRole(Role.USER);
        userEntity.setStatus(Status.INACTIVE);
        userEntity.setPassword(PasswordEncoder.encode(userRequest.getPassword()));

        try {
            userRepository.persist(userEntity);
            sendVerificationEmail(userEntity);
        } catch (Exception e) {
            throw new UserApiException("Problem while saving user", e);
        }

        return toUserResponse(userEntity);
    }

    // Method to get all users
    public List<UserRequest> getAllUsers() {
        try {
            return userRepository.listAll().stream()
                    .map(this::toUserResponse)
                    .toList();
        } catch (Exception e) {
            throw new UserApiException("Problem while getting all users", e);
        }
    }

    // Method to verify a user
    @Transactional
    public UserRequest verifyUser(String email, String verificationCode) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getVerifyCode().equals(verificationCode)) {
            user.setStatus(Status.ACTIVE);
            user.setVerifyCode(null);
        } else {
            throw new UserBadRequestException("Incorrect verification code or user already verified");
        }

        return toUserResponse(user);
    }

    // Method to get a specific user by ID
    public UserRequest getUser(UUID id) {
        UserEntity userEntity = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        return toUserResponse(userEntity);
    }

    // Method to update a user
    @Transactional
    public UserRequest updateUser(UUID id, UserRequest userRequest) {
        UserEntity existingUser = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        validateDuplicate(userRequest.getEmail(), id);

        updateUserEntity(existingUser, userRequest);

        userRepository.persist(existingUser);
        return toUserResponse(existingUser);
    }

    // Method to delete a user
    @Transactional
    public void deleteUser(UUID id) {
        UserEntity userEntity = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        userRepository.delete(userEntity);
    }

    // Helper method to validate if email is already taken
    private void validateDuplicate(String email, UUID id) {
        Optional<UserEntity> existingUser = (id == null)
                ? userRepository.findByEmail(email)
                : userRepository.findByEmailOrIdNot(email, id);

        existingUser.ifPresent(user -> {
            throw new UserAlreadyExistException("User already exists with this email: " + email);
        });
    }

    // Helper method to validate user creation request
    private void validateCreateUserRequest(UserRequest userRequest) {
        if (userRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }
        if (userRequest.getResetToken() != null || userRequest.getVerifyCode() != null ||
                userRequest.getStatus() != null || userRequest.getRole() != null) {
            throw new UserBadRequestException("Incorrect data for creating user");
        }
        validateDuplicate(userRequest.getEmail(), null);
    }

    // Helper method to remove sensitive or unwanted fields from the response
    private void removeSensitiveFields(UserRequest userRequest) {
        userRequest.setStatus(null);
        userRequest.setPassword(null);
        userRequest.setVerifyCode(null);
        userRequest.setResetToken(null);
    }

    // Helper method to send verification email
    private void sendVerificationEmail(UserEntity userEntity) {
        sendEmail.send(userEntity.getEmail(), "Verification", userEntity.getVerifyCode());
    }

    // Helper method to convert UserEntity to UserRequest and remove sensitive fields
    private UserRequest toUserResponse(UserEntity userEntity) {
        UserRequest userResponse = userEntity.toUserRequest();
        removeSensitiveFields(userResponse);
        return userResponse;
    }

    // Helper method to update the fields of an existing user
    private void updateUserEntity(UserEntity existingUser, UserRequest userRequest) {
        existingUser.setName(userRequest.getName());
        existingUser.setSurname(userRequest.getSurname());
        existingUser.setVerifyCode(userRequest.getVerifyCode());
        existingUser.setStatus(userRequest.getStatus());
    }
}
