package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    Optional<Role> findById(Long id);
    Optional<Role> findByRoleCode(String roleCode);
    Optional<Role> findByRoleName(String roleName);
    Page<Role> findAll(Pageable pageable);
    void deleteById(Long id);
    Role update(Role role);
}

