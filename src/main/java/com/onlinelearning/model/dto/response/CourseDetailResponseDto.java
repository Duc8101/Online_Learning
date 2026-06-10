package com.onlinelearning.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDetailResponseDto extends AllCoursesResponseDto {

    public CourseDetailResponseDto(int courseId, @NonNull String courseName, @NonNull String image, int categoryId, long creatorId, @NonNull String creatorName, String description, boolean lessonExist, boolean enrollCourseExist) {
        super(courseId, courseName, image, categoryId, creatorId, creatorName, description, lessonExist, enrollCourseExist);
    }

    List<LessonListForCourseDetailResponseDto> lessons = new ArrayList<>();
}
