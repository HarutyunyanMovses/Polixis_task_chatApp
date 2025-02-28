package com.jira.repository;

import com.jira.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    public Optional<UserEntity> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<UserEntity> findByEmailOrIdNot(String email, UUID id) {
        return find("email = ?1 AND id <> ?2", email, id).firstResultOptional();
    }

    public void delete(UserEntity user) {
        delete("id", user.getId());
    }
}
