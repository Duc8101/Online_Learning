package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
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
public class Client {

    @Id
    @UuidGenerator
    private String clientId;

    @Column(nullable = false)
    private String deviceInfo;

    @Column(nullable = false)
    private int failedLoginCount;

    @Column(columnDefinition = "datetime(0)")
    private Instant lockoutEndTime;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<UserClient> userClients = new HashSet<>();
}
