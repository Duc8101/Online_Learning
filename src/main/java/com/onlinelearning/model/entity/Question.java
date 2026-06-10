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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer questionId;

    @Column(nullable = false)
    String questionName;

    @Column(nullable = false)
    int quizId;

    @Column(nullable = false)
    String answer1;

    @Column(nullable = false)
    String answer2;

    @Column(nullable = false)
    String answer3;

    @Column(nullable = false)
    String answer4;

    @Column(nullable = false)
    int answerCorrect;

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
