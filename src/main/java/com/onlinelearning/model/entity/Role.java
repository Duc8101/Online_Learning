package com.onlinelearning.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer roleId;

    @Column(nullable = false)
    String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    Set<UserAccount> userAccounts = new HashSet<>();
}
