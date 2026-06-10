package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    Instant createdAt;

    @Column(columnDefinition = "datetime(0)")
    Instant updatedAt;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    Set<Pdf> pdfs = new HashSet<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    Set<Video> videos = new HashSet<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    Set<Quiz> quizzes = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
