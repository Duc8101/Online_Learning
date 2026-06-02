package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.request.VideoCreateRequestDto;
import com.onlinelearning.model.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "videoName", expression = "java(DTO.getVideoName().trim())")
    @Mapping(target = "fileVideo", expression = "java(DTO.getFileVideo().trim())")
    Video toVideo(VideoCreateRequestDto DTO);
}
