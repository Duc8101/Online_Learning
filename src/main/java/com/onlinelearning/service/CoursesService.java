package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import jakarta.servlet.http.HttpSession;

public interface CoursesService {

    ResponseBase list(Integer categoryId, Boolean orderBy, Integer page, HttpSession session);

    ResponseBase detail(int courseId, HttpSession session);

    ResponseBase enrollCourse(int courseId, long studentId);

    ResponseBase learnCourse(int courseId, String fileVideo, String name /*video name or pdf name*/, String filePdf, Integer lessonId, Integer videoId, Integer pdfId, long userId);

    ResponseBase delete(int courseId);
}
