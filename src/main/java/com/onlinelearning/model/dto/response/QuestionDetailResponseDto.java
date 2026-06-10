package com.onlinelearning.model.dto.response;

import com.onlinelearning.model.entity.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDetailResponseDto {

    Question question;
    int answerCorrect;
    long creatorId;

    public QuestionDetailResponseDto(Question question, long creatorId) {
        this.question = question;
        this.creatorId = creatorId;
    }
}
