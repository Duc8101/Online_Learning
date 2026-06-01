package com.onlinelearning.repository;

import com.onlinelearning.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Client findFirstByDeviceInfo(String deviceInfo);

    @Modifying
    @Query("update Client c set c.failedLoginCount = :failedLoginCount where c.clientId = :clientId")
    void setFailedLoginCount(int failedLoginCount, String clientId);

    @Modifying
    @Query("update Client c set c.lockoutEndTime = :lockoutEndTime, c.failedLoginCount = :failedLoginCount where c.clientId = :clientId")
    void setLockoutEndTimeAndFailedLoginCount(Instant lockoutEndTime, int failedLoginCount, String clientId);
}
