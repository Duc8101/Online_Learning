package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOrTeacherCoursesResponseDto {

    private int courseId;
    private String courseName;
    private String image;
    private int categoryId;
    private long creatorId;
    private String creatorName;
    private String description;
}
