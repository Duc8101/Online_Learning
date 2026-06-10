package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionListForStartQuizResponseDto {

    int questionId;
    String questionName;
    int quizId;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Integer chosenAnswer;
}
