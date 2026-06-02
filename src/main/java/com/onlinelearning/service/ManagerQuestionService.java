package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.QuestionCreateRequestDto;
import com.onlinelearning.model.dto.request.QuestionUpdateRequestDto;

public interface ManagerQuestionService {

    ResponseBase list(int quizId, long userId);

    ResponseBase create(int quizId, long userId);

    ResponseBase create(QuestionCreateRequestDto DTO);

    ResponseBase detail(int questionId, long userId);

    ResponseBase update(int questionId, long userId);

    ResponseBase update(int questionId, QuestionUpdateRequestDto DTO);

    ResponseBase delete(int questionId, long userId);
}
