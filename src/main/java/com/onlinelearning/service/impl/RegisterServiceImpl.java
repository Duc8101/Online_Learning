package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.UserMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.RegisterRequestDto;
import com.onlinelearning.model.entity.Role;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.model.entity.UserAccount;
import com.onlinelearning.model.enumeration.Gender;
import com.onlinelearning.model.enumeration.UserRole;
import com.onlinelearning.repository.RoleRepository;
import com.onlinelearning.repository.UserAccountRepository;
import com.onlinelearning.repository.UserRepository;
import com.onlinelearning.service.RegisterService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegisterServiceImpl extends BaseService implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserUtil userUtil;
    private final UserMapper userMapper;
    private final UserAccountRepository userAccountRepository;

    @Override
    public ResponseBase register() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, false, false, true);
        data.put("male", Gender.MALE.getDisplayName());
        data.put("female", Gender.FEMALE.getDisplayName());
        data.put("other", Gender.OTHER.getDisplayName());
        return new ResponseBase("register", data);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseBase register(RegisterRequestDto DTO) throws Exception {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, false, false, true);
        data.put("male", Gender.MALE.getDisplayName());
        data.put("female", Gender.FEMALE.getDisplayName());
        data.put("other", Gender.OTHER.getDisplayName());

        if (userRepository.isUsernameOrEmailExist(DTO.getUsername(), DTO.getEmail().trim())) {
            data.put("error", "Username or email already exists");
            return new ResponseBase("register", data);
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
        data.put("success", "Register successful");
        return new ResponseBase("register", data);
    }
}
