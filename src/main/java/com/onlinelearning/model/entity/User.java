package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[user]")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(columnDefinition = "char(10)")
    private String phone;

    @Column(nullable = false)
    private String image;

    @Column(columnDefinition = "nvarchar(100)")
    private String address;

    @Column(nullable = false, columnDefinition = "varchar(50)", unique = true)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, columnDefinition = "datetime(0)")
    @CreationTimestamp
    private Instant createdAt;

    @Column(columnDefinition = "datetime(0)")
    private Instant updatedAt;

    @OneToOne(mappedBy = "user")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<EnrollCourse> enrollCourses = new HashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Result> results = new HashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<StartQuiz> startQuizzes = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserClient> userClients = new HashSet<>();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
