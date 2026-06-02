package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateRequestDto {

    private String questionName;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int answerCorrect;
}
