package com.jira.entity;

import com.jira.enums.Role;
import com.jira.enums.Status;
import com.jira.requestModels.UserRequest;
import com.jira.utils.constants.DataBaseConstants;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = DataBaseConstants.SCHEMA_NAME, name = DataBaseConstants.USERS_TABLE_NAME)
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "verification_code")
    private String verifyCode;

    @Column(name = "reset_token")
    private String resetToken;

    public UserEntity(UserRequest request) {
        this.id = request.getId();
        this.name = request.getName();
        this.surname = request.getSurname();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.role = request.getRole();
        this.status = request.getStatus();
        this.verifyCode = request.getVerifyCode();
        this.resetToken = request.getResetToken();
    }

    public UserRequest toUserRequest() {
        return new UserRequest(id, name, surname, email, password, role, status, verifyCode, resetToken);
    }
}
