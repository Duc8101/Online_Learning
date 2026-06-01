package com.onlinelearning.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AllCoursesResponseDto extends StudentOrTeacherCoursesResponseDto {

    private boolean lessonExist;
    private boolean enrollCourseExist;

    public AllCoursesResponseDto(int courseId, @NonNull String courseName, @NonNull String image, int categoryId, long creatorId, @NonNull String creatorName, String description, boolean lessonExist, boolean enrollCourseExist) {
        super(courseId, courseName, image, categoryId, creatorId, creatorName, description);
        this.lessonExist = lessonExist;
        this.enrollCourseExist = enrollCourseExist;
    }
}
