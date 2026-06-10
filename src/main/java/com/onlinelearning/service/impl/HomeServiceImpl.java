package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.UserForHomePageResponseDto;
import com.onlinelearning.model.enumeration.UserRole;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.HomeService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeServiceImpl extends BaseService implements HomeService {

    final UserRepository userRepository;

    @Override
    public ResponseBase home() {
        Map<String, Object> data = new HashMap<>();
        List<UserForHomePageResponseDto> users = userRepository.getTop4Teachers(UserRole.TEACHER.getValue(), PageRequest.of(0, 4));

        data.put("users", users);
        data.put("code", HttpStatus.OK.value());
        setValueForHeaderFooter(data, true, true, true, true);
        return new ResponseBase().withData(data).withViewName("home");
    }
}
