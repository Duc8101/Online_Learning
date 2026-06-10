package com.onlinelearning.repository;

import com.onlinelearning.model.entity.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    @Modifying
    @Query("update UserAccount ua set ua.lockoutEndTime = :lockoutEndTime where ua.userAccountId = :userAccountId")
    void setLockoutEndTime(Instant lockoutEndTime, String userAccountId);

    @Modifying
    @Query("update UserAccount ua set ua.lockoutEndTime = :lockoutEndTime, ua.failedLoginCount = :failedLoginCount where ua.userAccountId = :userAccountId")
    void setLockoutEndTimeAndFailedLoginCount(Instant lockoutEndTime, int failedLoginCount, String userAccountId);

    @Modifying
    @Query("update UserAccount ua set ua.failedLoginCount = :failedLoginCount where ua.userAccountId = :userAccountId")
    void setFailedLoginCount(int failedLoginCount, String userAccountId);

    @Transactional
    @Modifying
    @Query("UPDATE UserAccount u SET u.password = :password, u.updatedAt = :updatedAt where u.userId = :userId")
    void updatePassword(String password, Instant updatedAt, long userId);

    @Query("select u.password from UserAccount u where u.userId = :userId")
    String getPassword(long userId);
}
