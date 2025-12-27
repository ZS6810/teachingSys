package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "role_code", name = "role_code"),
        @UniqueConstraint(columnNames = "role_name", name = "role_name")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Column(name = "role_code", nullable = false, length = 50)
    private String roleCode;

    @Column(name = "description")
    private String description;

    @Column(name = "permissions", columnDefinition = "json")
    private String permissions;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;
}

