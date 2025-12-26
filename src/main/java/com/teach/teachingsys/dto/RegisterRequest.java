package com.teach.teachingsys.dto;

import com.teach.teachingsys.entity.enums.UserEnums.UserType;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String realName;
    private String phone;
    private UserType userType; // student, teacher, admin
    private String qualificationProof; // 教师需要提供资质证明
}

