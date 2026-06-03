package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.request.RegisterRequestDto;
import com.onlinelearning.model.dto.request.TeacherCreateRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.util.DataUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DataUtil.class})
public interface UserMapper {

    @Mapping(target = "username", source = "userAccount.username")
    @Mapping(target = "roleId", source = "userAccount.role.roleId")
    UserProfileResponseDto toUserProfileResponseDto(User user);

    @Mapping(target = "fullName", expression = "java(dto.getFullName().trim())")
    @Mapping(target = "email", expression = "java(dto.getEmail().trim())")
    @Mapping(target = "address", expression = "java(DataUtil.trimToNull(dto.getAddress()))")
    @Mapping(target = "phone", expression = "java(DataUtil.trimToNull(dto.getPhone()))")
    User toUser(RegisterRequestDto dto);

    @Mapping(target = "fullName", expression = "java(dto.getFullName().trim())")
    @Mapping(target = "email", expression = "java(dto.getEmail().trim())")
    @Mapping(target = "phone", expression = "java(DataUtil.trimToNull(dto.getPhone()))")
    User toUser(TeacherCreateRequestDto dto);
}
