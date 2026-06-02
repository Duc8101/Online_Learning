package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.request.CourseCreateUpdateRequestDto;
import com.onlinelearning.model.entity.Course;
import com.onlinelearning.util.DataUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DataUtil.class})
public interface CourseMapper {

    @Mapping(target = "courseName", expression = "java(DTO.getCourseName().trim())")
    @Mapping(target = "description", expression = "java(DataUtil.trimToNull(DTO.getDescription()))")
    @Mapping(target = "image", expression = "java(DTO.getImage().trim())")
    Course toCourse(CourseCreateUpdateRequestDto DTO);
}
