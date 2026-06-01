package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.UserForHomePageResponseDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
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

    @Query("select new com.onlinelearning.model.dto.response.UserProfileResponseDto(u.userId, u.fullName, u.phone, u.image"
            + ", u.address, u.email, u.gender, u.userAccount.username, u.userAccount.role.roleId) from User u\n"
            + "where u.userId = :userId")
    UserProfileResponseDto getUserProfile(long userId);

    @Query("select u from User u left join fetch u.userAccount ua left join fetch ua.role where u.userAccount.username = :username")
    User getFirstByUsername(String username);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    Long getUserId(String email);
}
