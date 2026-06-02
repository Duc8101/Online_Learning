package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListForManagerQuestionResponseDto {

    private int questionId;
    private String questionName;
    private int quizId;
}
