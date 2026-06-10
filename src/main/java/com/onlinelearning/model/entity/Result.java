package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {

    @Id
    @UuidGenerator
    String resultId;

    @Column(nullable = false)
    int quizId;

    @Column(nullable = false)
    long studentId;

    @Column(nullable = false)
    double score;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    Instant submittedAt;
}
