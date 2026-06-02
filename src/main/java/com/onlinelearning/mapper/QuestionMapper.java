package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.request.QuestionCreateRequestDto;
import com.onlinelearning.model.entity.Question;
import com.onlinelearning.util.DataUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DataUtil.class})
public interface QuestionMapper {

    @Mapping(target = "answer1", expression = "java(DTO.getAnswer1().trim())")
    @Mapping(target = "answer2", expression = "java(DTO.getAnswer2().trim())")
    @Mapping(target = "answer3", expression = "java(DataUtil.trimToNull(DTO.getAnswer3()))")
    @Mapping(target = "answer4", expression = "java(DataUtil.trimToNull(DTO.getAnswer4()))")
    @Mapping(target = "answerCorrect", ignore = true)
    Question toQuestion(QuestionCreateRequestDto DTO);
}
