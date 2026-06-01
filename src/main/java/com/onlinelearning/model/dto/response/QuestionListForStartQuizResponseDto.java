package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListForStartQuizResponseDto {

    private int questionId;
    private String questionName;
    private int quizId;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer chosenAnswer;
}
