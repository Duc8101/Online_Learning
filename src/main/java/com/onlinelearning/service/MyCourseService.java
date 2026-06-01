package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;

public interface MyCourseService {

    ResponseBase myCourse(Integer page, long userId);
}
