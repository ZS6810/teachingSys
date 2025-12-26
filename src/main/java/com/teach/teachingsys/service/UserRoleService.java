package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    Optional<UserRole> findById(Long id);
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
    Page<UserRole> findAll(Pageable pageable);
    void deleteById(Long id);
    UserRole update(UserRole userRole);
}

