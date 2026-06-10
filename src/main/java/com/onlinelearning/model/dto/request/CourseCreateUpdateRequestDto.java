package com.onlinelearning.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseCreateUpdateRequestDto {

    String courseName;
    String image;
    int categoryId;
    String description;
}
