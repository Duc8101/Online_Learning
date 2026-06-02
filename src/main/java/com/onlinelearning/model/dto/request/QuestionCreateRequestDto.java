package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCreateRequestDto {

    private String questionName;
    private int quizId;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int answerCorrect;
}
