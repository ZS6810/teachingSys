package com.teach.teachingsys.service;

import com.teach.teachingsys.dto.AuthResponse;
import com.teach.teachingsys.dto.LoginRequest;
import com.teach.teachingsys.dto.RegisterRequest;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.entity.UserRole;
import com.teach.teachingsys.entity.enums.UserEnums.UserStatus;
import com.teach.teachingsys.entity.enums.UserEnums.UserType;
import com.teach.teachingsys.repository.RoleRepository;
import com.teach.teachingsys.repository.UserRepository;
import com.teach.teachingsys.repository.UserRoleRepository;
import com.teach.teachingsys.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    /**
     * 用户注册
     */
    public AuthResponse register(RegisterRequest request) {
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }
        
        // 创建用户
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(PasswordUtil.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRealName(request.getRealName());
        user.setPhone(request.getPhone());
        user.setUserType(request.getUserType());
        
        // 根据用户类型设置状态
        if (request.getUserType() == UserType.teacher) {
            user.setStatus(UserStatus.pending); // 教师需要审核
            user.setQualificationProof(request.getQualificationProof());
        } else {
            user.setStatus(UserStatus.active); // 学生直接激活
        }
        
        user = userRepository.save(user);
        
        // 分配默认角色
        assignDefaultRole(user, request.getUserType());
        
        return buildAuthResponse(user);
    }

    /**
     * 用户登录
     */
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));
        
        // 验证密码
        if (!PasswordUtil.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 检查用户状态
        if (user.getStatus() == UserStatus.inactive) {
            throw new RuntimeException("账户已被禁用，请联系管理员");
        }
        
        // 更新最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
        
        return buildAuthResponse(user);
    }

    /**
     * 分配默认角色
     */
    private void assignDefaultRole(User user, UserType userType) {
        String roleCode = switch (userType) {
            case student -> "STUDENT";
            case teacher -> "TEACHER";
            case admin -> "ADMIN";
        };
        
        roleRepository.findByRoleCode(roleCode).ifPresent(role -> {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);
        });
    }

    /**
     * 构建认证响应
     */
    private AuthResponse buildAuthResponse(User user) {
        List<String> roles = userRoleRepository.findByUserId(user.getId())
                .stream()
                .map(ur -> ur.getRole().getRoleCode())
                .collect(Collectors.toList());
        
        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .realName(user.getRealName())
                .userType(user.getUserType())
                .status(user.getStatus())
                .roles(roles)
                .build();
    }
}

