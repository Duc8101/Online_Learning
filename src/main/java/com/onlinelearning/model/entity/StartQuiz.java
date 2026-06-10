package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StartQuiz {

    @Id
    @UuidGenerator
    String startQuizId;

    @Column(nullable = false)
    long studentId;

    @Column(nullable = false)
    int questionId;

    @Column
    Integer answer;
}
