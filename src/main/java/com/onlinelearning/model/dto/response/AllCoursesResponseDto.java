package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllCoursesResponseDto extends StudentOrTeacherCoursesResponseDto {

    boolean lessonExist;
    boolean enrollCourseExist;

    public AllCoursesResponseDto(int courseId, @NonNull String courseName, @NonNull String image, int categoryId, long creatorId, @NonNull String creatorName, String description, boolean lessonExist, boolean enrollCourseExist) {
        super(courseId, courseName, image, categoryId, creatorId, creatorName, description);
        this.lessonExist = lessonExist;
        this.enrollCourseExist = enrollCourseExist;
    }
}
