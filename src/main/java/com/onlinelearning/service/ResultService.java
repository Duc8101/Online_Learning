package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;

public interface ResultService {

    ResponseBase result(int quizId, long userId);
}
