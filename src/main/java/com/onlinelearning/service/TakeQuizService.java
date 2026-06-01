package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;

public interface TakeQuizService {

    ResponseBase takeQuiz(int lessonId, long userId);
}
