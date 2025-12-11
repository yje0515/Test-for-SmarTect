package com.smartect.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNo;

    @Column(name="user_id",unique = true,nullable = false)
    private String userId;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {ADMIN,STAFF}

}
