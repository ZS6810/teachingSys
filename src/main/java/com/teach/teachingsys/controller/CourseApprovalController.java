package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.service.CourseApprovalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course-approval")
@RequiredArgsConstructor
public class CourseApprovalController {

    private final CourseApprovalService approvalService;

    /**
     * 提交课程审核（教师）
     */
    @PostMapping("/submit/{courseId}")
    public ResponseEntity<ApiResponse<Course>> submitForApproval(
            @PathVariable Long courseId,
            HttpSession session) {
        try {
            Course course = approvalService.submitForApproval(courseId);
            return ResponseEntity.ok(ApiResponse.success("已提交审核", course));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 审核通过课程（管理员）
     */
    @PostMapping("/approve/{courseId}")
    public ResponseEntity<ApiResponse<Course>> approveCourse(@PathVariable Long courseId) {
        try {
            Course course = approvalService.approveCourse(courseId);
            return ResponseEntity.ok(ApiResponse.success("课程审核通过", course));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 发布课程（管理员或教师）
     */
    @PostMapping("/publish/{courseId}")
    public ResponseEntity<ApiResponse<Course>> publishCourse(@PathVariable Long courseId) {
        try {
            Course course = approvalService.publishCourse(courseId);
            return ResponseEntity.ok(ApiResponse.success("课程已发布", course));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 拒绝课程（管理员）
     */
    @PostMapping("/reject/{courseId}")
    public ResponseEntity<ApiResponse<Course>> rejectCourse(
            @PathVariable Long courseId,
            @RequestBody Map<String, String> request) {
        try {
            String reason = request.get("reason");
            Course course = approvalService.rejectCourse(courseId, reason);
            return ResponseEntity.ok(ApiResponse.success("课程已拒绝", course));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 获取待审核课程列表（管理员）
     */
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Course>>> getPendingCourses() {
        List<Course> courses = approvalService.getPendingCourses();
        return ResponseEntity.ok(ApiResponse.success(courses));
    }
}

