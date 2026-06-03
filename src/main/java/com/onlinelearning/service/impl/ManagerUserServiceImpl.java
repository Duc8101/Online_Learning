package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.UserMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.TeacherCreateRequestDto;
import com.onlinelearning.model.dto.response.UserListNotAdminResponseDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.entity.Role;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.model.entity.UserAccount;
import com.onlinelearning.model.enumeration.Gender;
import com.onlinelearning.model.enumeration.UserRole;
import com.onlinelearning.repository.RoleRepository;
import com.onlinelearning.repository.UserAccountRepository;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.ManagerUserService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerUserServiceImpl extends BaseService implements ManagerUserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final UserUtil userUtil;
    final UserMapper userMapper;
    final UserAccountRepository userAccountRepository;

    @Override
    public ResponseBase list(String name) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        List<UserListNotAdminResponseDto> users = userRepository.getUsersNotAdmin(UserRole.ADMIN.getValue(), name == null ? null : name.trim());

        data.put("users", users);
        data.put("name", name == null ? "" : name.trim());
        return new ResponseBase("manager_user/list", data);
    }

    @Override
    public ResponseBase detail(long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        UserProfileResponseDto user = userRepository.getUserProfile(userId);
        if (user == null) {
            data.put("error", "User not found");
            return new ResponseBase("shared/error", data);
        }

        List<String> genders = Arrays.stream(Gender.values()).map(Gender::getDisplayName).toList();
        data.put("user", user);
        data.put("genders", genders);
        return new ResponseBase("manager_user/detail", data);
    }

    @Override
    public ResponseBase create() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        List<String> genders = Arrays.stream(Gender.values()).map(Gender::getDisplayName).toList();
        data.put("genders", genders);
        return new ResponseBase("manager_user/create", data);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseBase create(TeacherCreateRequestDto DTO) throws Exception {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        List<String> genders = Arrays.stream(Gender.values()).map(Gender::getDisplayName).toList();
        data.put("genders", genders);

        if (userRepository.isUsernameOrEmailExist(DTO.getUsername(), DTO.getEmail().trim())) {
            data.put("error", "Username or email already exists");
            return new ResponseBase("manager_user/create", data);
        }

        String newPw = userUtil.randomPassword();
        String hashPw = userUtil.hashPassword(newPw);

        User user = userMapper.toUser(DTO);
        user.setImage("https://i.pinimg.com/564x/31/ec/2c/31ec2ce212492e600b8de27f38846ed7.jpg");
        userRepository.save(user);

        Role role = roleRepository.findById(UserRole.STUDENT.getValue()).orElse(null);
        if (role == null) {
            data.clear();
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Role not found");
            return new ResponseBase("shared/error", data);
        }

        UserAccount userAccount = UserAccount.builder()
                .user(user).username(DTO.getUsername())
                .password(hashPw).role(role)
                .build();
        userAccountRepository.save(userAccount);

        String body = userUtil.bodyEmailForRegister(newPw);
        userUtil.sendEmail("Welcome to Online Learn", body, DTO.getEmail().trim());

        data.put("success", "Create teacher successful");
        return new ResponseBase("manager_user/create", data);
    }
}
