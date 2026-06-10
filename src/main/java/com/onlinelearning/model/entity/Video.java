package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer videoId;

    @Column(nullable = false)
    String videoName;

    @Column(nullable = false)
    String fileVideo;

    @Column(nullable = false)
    int lessonId;

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
