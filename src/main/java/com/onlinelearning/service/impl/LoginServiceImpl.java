package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.UserMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.repository.UserAccountRepository;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.LoginService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.DataUtil;
import com.onlinelearning.util.UserUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginServiceImpl extends BaseService implements LoginService {

    final UserRepository userRepository;
    final UserAccountRepository userAccountRepository;
    final UserUtil userUtil;
    final UserMapper userMapper;

    static final int MAX_FAILED_LOGIN_ATTEMPTS = 5;

    @Override
    public ResponseBase login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        // get all cookies
        Cookie[] cookies = request.getCookies();

        String id = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    id = cookie.getValue();
                    break;
                }
            }
        }

        if (id == null) {
            setValueForHeaderFooter(data, true, false, false, true);
            return new ResponseBase("login", data);
        }

        Long userId = DataUtil.parseToLong(id, null);

        if (userId == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "User login info is invalid");
            return new ResponseBase("shared/error", data);
        }

        UserProfileResponseDto user = userRepository.getUserProfile(userId);
        if (user == null) {
            Cookie cookie = new Cookie("userId", userId + "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            setValueForHeaderFooter(data, true, true, true, true);
            return new ResponseBase("shared/error", data);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return new ResponseBase("redirect:/Home", data);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseBase login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
        Map<String, Object> data = new HashMap<>();
        User user = userRepository.getFirstByUsername(username);

        // nếu sai thông tin tên đăng nhập
        if (user == null) {
            data.put("error", "This account is not registered");
            return new ResponseBase().withData(data).withViewName("login");
        }

        // nếu đang bị khóa tài khoản
        if (user.getUserAccount().getLockoutEndTime() != null && user.getUserAccount().getLockoutEndTime().isAfter(Instant.now())) {
            setValueForHeaderFooter(data, true, false, false, true);

            // nếu đúng mật khẩu
            if (user.getUserAccount().getPassword().equals(userUtil.hashPassword(password))) {
                int numberMinutes = user.getUserAccount().getLockoutEndTime().atZone(ZoneId.systemDefault()).getMinute() - Instant.now().atZone(ZoneId.systemDefault()).getMinute();
                String strMinutes = user.getUserAccount().getLockoutEndTime().atZone(ZoneId.systemDefault()).getMinute() - Instant.now().atZone(ZoneId.systemDefault()).getMinute() == 1 ? "minute" : "minutes";

                data.put("error", String.format("Your account is locked. Please come back after %d %s", numberMinutes, strMinutes));
                return new ResponseBase().withData(data).withViewName("login");
            }

            userAccountRepository.setLockoutEndTime(Instant.now().plusSeconds(5 * 60), user.getUserAccount().getUserAccountId());
            data.put("error", "Your account is still being locked. Every attempt locks longer");
            return new ResponseBase().withData(data).withViewName("login");
        }

        // nếu sai mật khẩu
        if (!user.getUserAccount().getPassword().equals(userUtil.hashPassword(password))) {
            setValueForHeaderFooter(data, true, false, false, true);

            int userFailedLoginCount = user.getUserAccount().getFailedLoginCount() + 1;
            userAccountRepository.setLockoutEndTimeAndFailedLoginCount(Instant.now().plusSeconds(10 * 60), userFailedLoginCount, user.getUserAccount().getUserAccountId());

            // nếu nhập sai đến mức tối đa
            if (userFailedLoginCount >= MAX_FAILED_LOGIN_ATTEMPTS) {
                data.put("error", ("Your account is locked. Please come back after 15 minutes"));
                return new ResponseBase().withData(data).withViewName("login");
            }

            data.put("error", "Information login not correct");
            return new ResponseBase().withData(data).withViewName("login");
        }

        userAccountRepository.setFailedLoginCount(0, user.getUserAccount().getUserAccountId());

        UserProfileResponseDto userDto = userMapper.toUserProfileResponseDto(user);
        session.setAttribute("user", userDto);

        Cookie cookie = new Cookie("userId", user.getUserId().toString());
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return new ResponseBase("redirect:/Home", data);
    }
}
