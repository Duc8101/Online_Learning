package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ChangePasswordRequestDto;
import com.onlinelearning.repository.UserAccountRepository;
import com.onlinelearning.service.ChangePasswordService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.UserUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordServiceImpl extends BaseService implements ChangePasswordService {

    final UserAccountRepository userAccountRepository;
    final UserUtil userUtil;

    @Override
    public ResponseBase changePassword() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        return new ResponseBase("change_password", data);
    }

    @Override
    public ResponseBase changePassword(ChangePasswordRequestDto DTO, long userId) throws Exception {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        String password = userAccountRepository.getPassword(userId);

        if (password == null) {
            data.put("error", "User not found");
            return new ResponseBase("shared/error", data);
        }

        if (!password.equals(userUtil.hashPassword(DTO.getCurrentPassword()))) {
            data.put("error", "Current password not correct");
            return new ResponseBase().withData(data).withViewName("change_password");
        }

        userAccountRepository.updatePassword(userUtil.hashPassword(DTO.getNewPassword()), Instant.now(), userId);
        data.put("success", "Update successful");
        return new ResponseBase().withData(data).withViewName("change_password");
    }
}
