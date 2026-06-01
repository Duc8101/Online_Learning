package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.StartQuizRequestDto;

public interface StartQuizService {

    ResponseBase startQuiz(int quizId, long userId);

    ResponseBase startQuiz(StartQuizRequestDto DTO, int minutes, int questionNo, int seconds, long userId, String button);
}
