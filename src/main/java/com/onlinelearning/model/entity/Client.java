package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {

    @Id
    @UuidGenerator
    String clientId;

    @Column(nullable = false)
    String deviceInfo;

    @Column(nullable = false)
    int failedLoginCount;

    @Column(columnDefinition = "datetime(0)")
    Instant lockoutEndTime;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    Instant createdAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    Set<UserClient> userClients = new HashSet<>();
}
