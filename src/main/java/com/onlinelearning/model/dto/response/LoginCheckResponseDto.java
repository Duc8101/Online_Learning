package com.onlinelearning.model.dto.response;

import com.onlinelearning.model.entity.User;
import com.onlinelearning.model.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCheckResponseDto {

    private User user;
    private UserAccount userAccount;
}
