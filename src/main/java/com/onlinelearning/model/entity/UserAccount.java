package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAccount {

    @Id
    @UuidGenerator
    String userAccountId;

    @Column(nullable = false)
    long userId;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(30)")
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    int roleId;

    @Column(nullable = false)
    int failedLoginCount;

    @Column(columnDefinition = "datetime(0)")
    Instant lockoutEndTime;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    Instant createdAt;

    @Column(columnDefinition = "datetime(0)")
    Instant updatedAt;

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
