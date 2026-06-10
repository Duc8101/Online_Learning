package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.ProfileRequestDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.enumeration.Gender;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.ProfileService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.DataUtil;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileServiceImpl extends BaseService implements ProfileService {

    final UserRepository userRepository;

    @Override
    public ResponseBase profile() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        List<String> genders = Arrays.stream(Gender.values()).map(Gender::getDisplayName).toList();
        data.put("genders", genders);
        return new ResponseBase("profile", data);
    }

    @Override
    public ResponseBase profile(ProfileRequestDto DTO, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        List<String> genders = Arrays.stream(Gender.values()).map(Gender::getDisplayName).toList();
        data.put("genders", genders);

        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");

        if (userRepository.isEmailExist(DTO.getEmail().trim(), user.getUserId())) {
            return new ResponseBase("profile", data);
        }

        user.setFullName(DTO.getFullName().trim());
        user.setPhone(DataUtil.trimToNull(DTO.getPhone()));
        user.setEmail(DTO.getEmail().trim());
        user.setAddress(DataUtil.trimToNull(DTO.getAddress()));
        user.setGender(DTO.getGender());

        if (!DTO.getImage().trim().isEmpty()) {
            user.setImage(String.format("/img/%s",  DTO.getImage().trim()));
        }

        userRepository.updateProfile(user.getFullName(), user.getPhone(), user.getEmail(), user.getAddress()
                , user.getGender(), user.getImage(), Instant.now(), user.getUserId());

        session.setAttribute("user", user);
        data.put("success", "Update successful");
        return new ResponseBase("profile", data);
    }
}
