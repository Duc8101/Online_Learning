package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    Quiz quiz;

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

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    Set<StartQuiz> startQuizzes = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
