package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer lessonId;

    @Column(nullable = false, columnDefinition = "nvarchar(200)")
    String lessonName;

    @Column(nullable = false)
    int courseId;

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
