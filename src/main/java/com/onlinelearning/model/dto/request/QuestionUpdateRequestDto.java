package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionUpdateRequestDto {

    String questionName;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int answerCorrect;
}
