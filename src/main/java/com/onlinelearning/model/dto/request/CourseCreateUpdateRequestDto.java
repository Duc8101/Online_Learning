package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateUpdateRequestDto {

    private String courseName;
    private String image;
    private int categoryId;
    private String description;
}
