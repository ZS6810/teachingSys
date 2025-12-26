package com.teach.teachingsys.dto;

import com.teach.teachingsys.entity.enums.UserEnums.UserStatus;
import com.teach.teachingsys.entity.enums.UserEnums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String username;
    private String email;
    private String realName;
    private UserType userType;
    private UserStatus status;
    private List<String> roles; // 角色代码列表
    private String message;
}

