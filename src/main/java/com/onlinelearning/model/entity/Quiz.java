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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer quizId;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    String quizName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    Lesson lesson;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    Instant createdAt;

    @Column(columnDefinition = "datetime(0)")
    Instant updatedAt;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    Set<Result> results = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
