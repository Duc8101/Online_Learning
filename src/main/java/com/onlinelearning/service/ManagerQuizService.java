package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuizCreateRequestDto;
import com.onlinelearning.model.dto.request.QuizUpdateRequestDto;

public interface ManagerQuizService {

    ResponseBase list(int lessonId, long userId);

    ResponseBase create(int lessonId, long userId);

    ResponseBase create(QuizCreateRequestDto DTO);

    ResponseBase update(int quizId, long userId);

    ResponseBase update(int quizId, QuizUpdateRequestDto DTO);

    ResponseBase delete(int quizId, long userId);
}
