package com.onlinelearning.model.dto.response;

import com.onlinelearning.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailResponseDto {

    private Question question;
    private int answerCorrect;
    private long creatorId;

    public QuestionDetailResponseDto(Question question, long creatorId) {
        this.question = question;
        this.creatorId = creatorId;
    }
}
