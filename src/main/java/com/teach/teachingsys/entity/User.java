package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teach.teachingsys.entity.enums.UserEnums.UserStatus;
import com.teach.teachingsys.entity.enums.UserEnums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`user`", indexes = {
        @Index(name = "idx_user_username", columnList = "username")
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = "username", name = "username"),
        @UniqueConstraint(columnNames = "email", name = "email")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "real_name", nullable = false, length = 100)
    private String realName;

    private String phone;

    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.active;

    @Column(name = "qualification_proof")
    private String qualificationProof;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

