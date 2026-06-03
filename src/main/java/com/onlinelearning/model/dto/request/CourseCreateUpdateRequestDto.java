package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseCreateUpdateRequestDto {

    String courseName;
    String image;
    int categoryId;
    String description;
}
