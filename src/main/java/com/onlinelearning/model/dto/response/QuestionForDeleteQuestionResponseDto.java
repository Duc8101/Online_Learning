package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionForDeleteQuestionResponseDto {

    private int courseId;
    private int quizId;
}
