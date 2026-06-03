package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;

public interface ViewLessonService {

    ResponseBase viewLesson(int courseId, String fileVideo, String name, String filePdf, Integer lessonId);
}
