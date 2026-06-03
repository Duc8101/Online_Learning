package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserListNotAdminResponseDto {

    private long userId;
    private String username;
    private int roleId;
    private String roleName;
}
