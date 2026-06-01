package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    @Column(nullable = false, columnDefinition = "nvarchar(200)")
    private String lessonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    private Instant createdAt;

    @Column(columnDefinition = "datetime(0)")
    private Instant updatedAt;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private Set<Pdf> pdfs = new HashSet<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private Set<Video> videos = new HashSet<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private Set<Quiz> quizzes = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
