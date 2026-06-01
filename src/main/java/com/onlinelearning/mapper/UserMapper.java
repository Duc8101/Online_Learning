package com.onlinelearning.mapper;

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
}
