package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.LoginCheckResponseDto;
import com.onlinelearning.model.dto.response.UserForHomePageResponseDto;
import com.onlinelearning.model.dto.response.UserListNotAdminResponseDto;
import com.onlinelearning.model.dto.response.UserProfileResponseDto;
import com.onlinelearning.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.UserForHomePageResponseDto(u.userId, u.fullName, u.image)
            FROM User u join UserAccount ua on u.userId = ua.userId
            WHERE ua.roleId = :role""")
    List<UserForHomePageResponseDto> getTop4Teachers(int role, Pageable pageable);

    @Query("""
            select new com.onlinelearning.model.dto.response.UserProfileResponseDto(u.userId, u.fullName, u.phone, u.image
            , u.address, u.email, u.gender, ua.username, ua.roleId)\s
            from User u join UserAccount ua on u.userId = ua.userId
            where u.userId = :userId""")
    UserProfileResponseDto getUserProfile(long userId);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.LoginCheckResponseDto(u, ua)\s
            from User u join UserAccount ua on u.userId = ua.userId
            where ua.username = :username
            """)
    LoginCheckResponseDto getFirstByUsername(String username);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    Long getUserId(String email);

    @Query("""
            select exists (
            SELECT 1 from User u join UserAccount ua on u.userId = ua.userId where ua.username = :username or u.email = :email
            )""")
    boolean isUsernameOrEmailExist(String username, String email);

    @Query("""
            select exists (
            SELECT 1 from User u where u.email = :email and u.userId <> :userId
            )""")
    boolean isEmailExist(String email, long userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.fullName = :fullName, u.phone = :phone, u.email = :email, u.address = :address, u.gender = :gender, u.image = :image, u.updatedAt = :updatedAt where u.userId = :userId")
    void updateProfile(String fullName, String phone, String email, String address, String gender, String image, Instant updatedAt, long userId);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.UserListNotAdminResponseDto(u.userId, ua.username, ua.roleId, r.roleName)
            from User u join UserAccount ua on u.userId = ua.userId join Role r on ua.roleId = r.roleId
            where ua.roleId <> :roleId and ((:name is null or :name = '') or ua.username like CONCAT('%', :name, '%'))""")
    List<UserListNotAdminResponseDto> getUsersNotAdmin(int roleId, String name);
}
