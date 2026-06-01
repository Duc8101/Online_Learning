package com.onlinelearning.model.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDetailResponseDto extends AllCoursesResponseDto {

    public CourseDetailResponseDto(int courseId, @NonNull String courseName, @NonNull String image, int categoryId, long creatorId, @NonNull String creatorName, String description, boolean lessonExist, boolean enrollCourseExist) {
        super(courseId, courseName, image, categoryId, creatorId, creatorName, description, lessonExist, enrollCourseExist);
    }

    private List<LessonListForCourseDetailResponseDto> lessons = new ArrayList<>();
}
