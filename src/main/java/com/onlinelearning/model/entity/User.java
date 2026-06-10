package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "[user]")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(nullable = false)
    String fullName;

    @Column(columnDefinition = "char(10)")
    String phone;

    @Column(nullable = false)
    String image;

    @Column(columnDefinition = "nvarchar(100)")
    String address;

    @Column(nullable = false, columnDefinition = "varchar(50)", unique = true)
    String email;

    @Column(nullable = false)
    String gender;

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
