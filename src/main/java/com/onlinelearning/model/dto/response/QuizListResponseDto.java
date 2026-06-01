package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizListResponseDto {

    private int quizId;
    private String quizName;
    private int lessonId;
}
