package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentOrTeacherCoursesResponseDto {

    int courseId;
    String courseName;
    String image;
    int categoryId;
    long creatorId;
    String creatorName;
    String description;
}
