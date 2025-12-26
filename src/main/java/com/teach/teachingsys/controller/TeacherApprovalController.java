package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.service.TeacherApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-approval")
@RequiredArgsConstructor
public class TeacherApprovalController {

    private final TeacherApprovalService approvalService;

    /**
     * 获取待审核教师列表（管理员）
     */
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<User>>> getPendingTeachers() {
        List<User> teachers = approvalService.getPendingTeachers();
        return ResponseEntity.ok(ApiResponse.success(teachers));
    }

    /**
     * 审核通过教师（管理员）
     */
    @PostMapping("/approve/{teacherId}")
    public ResponseEntity<ApiResponse<User>> approveTeacher(@PathVariable Long teacherId) {
        try {
            User teacher = approvalService.approveTeacher(teacherId);
            return ResponseEntity.ok(ApiResponse.success("教师审核通过", teacher));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 拒绝教师申请（管理员）
     */
    @PostMapping("/reject/{teacherId}")
    public ResponseEntity<ApiResponse<User>> rejectTeacher(@PathVariable Long teacherId) {
        try {
            User teacher = approvalService.rejectTeacher(teacherId);
            return ResponseEntity.ok(ApiResponse.success("教师申请已拒绝", teacher));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}

