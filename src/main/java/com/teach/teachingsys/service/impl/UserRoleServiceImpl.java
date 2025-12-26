package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.UserRole;
import com.teach.teachingsys.repository.UserRoleRepository;
import com.teach.teachingsys.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleRepository.findByUserIdAndRoleId(userId, roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleRepository.existsByUserIdAndRoleId(userId, roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserRole> findAll(Pageable pageable) {
        return userRoleRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRole update(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}

