package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.UserForHomePageResponseDto;
import com.onlinelearning.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.UserForHomePageResponseDto(u.userId, u.fullName, u.image)
            FROM User u
            WHERE u.userAccount.role.roleId = :role""")
    List<UserForHomePageResponseDto> getTop4Teachers(int role, Pageable pageable);
}
