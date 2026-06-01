package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartQuizRequestDto {

    private int questionId;
    private int quizId;
    private Integer answer;
}
