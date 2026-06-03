package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionUpdateRequestDto {

    String questionName;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int answerCorrect;
}
