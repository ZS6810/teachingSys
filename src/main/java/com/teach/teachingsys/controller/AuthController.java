package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.dto.AuthResponse;
import com.teach.teachingsys.dto.LoginRequest;
import com.teach.teachingsys.dto.RegisterRequest;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("注册成功", response));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request, HttpSession session, jakarta.servlet.http.HttpServletRequest httpRequest) {
        try {
            AuthResponse response = authService.login(request);

            // --- 核心修复步骤开始 ---
            // 1. 手动创建认证令牌（假设你的 roles 已经转换成了 GrantedAuthority）
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    response.getUsername(), null, null); // 这里的 null 可以换成真实的权限列表

            // 2. 将认证信息存入 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(token);

            // 3. 必须显式地将 SecurityContext 存入 Session！
            // 否则下一个请求进来时，Spring Security 找不到认证信息
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
            // --- 核心修复步骤结束 ---

            // 你原有的其他 session 存储
            session.setAttribute("userId", response.getUserId());

            return ResponseEntity.ok(ApiResponse.success("登录成功", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.success("登出成功", null));
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<AuthResponse>> getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("未登录"));
        }

        // 1. 调用 Service 层根据 ID 查询完整的 User 实体
        // 假设你的 authService 或 userService 有这个方法
        User user = authService.getUserById(userId);

        // 2. 将 Entity 转换为 AuthResponse
        AuthResponse response = AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .realName(user.getRealName())
                .userType(user.getUserType())
                .status(user.getStatus())
                // 如果有角色表，这里加载 roles
                // .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

