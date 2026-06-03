package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.repository.UserAccountRepository;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.ForgotPasswordService;
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
public class ForgotPasswordServiceImpl extends BaseService implements ForgotPasswordService {

    final UserRepository userRepository;
    final UserUtil userUtil;
    final UserAccountRepository userAccountRepository;

    @Override
    public ResponseBase forgotPassword() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        return new ResponseBase().withData(data).withViewName("forgot_password");
    }

    @Override
    public ResponseBase forgotPassword(String email) throws Exception {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, false, false, true);
        Long userId = userRepository.getUserId(email);

        // if not found email
        if (userId == null) {
            data.put("error", "Email not found");
            return new ResponseBase().withData(data).withViewName("forgot_password");
        }

        String randomPw = userUtil.randomPassword();
        String hashPw = userUtil.hashPassword(randomPw);

        String body = userUtil.bodyEmailForForgotPassword(randomPw);
        userUtil.sendEmail("Welcome to Online Learn", body, email.trim());

        userAccountRepository.updatePassword(hashPw, Instant.now(), userId);
        data.put("success", "Password was reset. Please check your email");
        return new ResponseBase().withData(data).withViewName("forgot_password");
    }
}
