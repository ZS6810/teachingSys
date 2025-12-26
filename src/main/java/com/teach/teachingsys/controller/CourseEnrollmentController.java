package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.service.CourseEnrollmentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-enrollment")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CourseEnrollmentService enrollmentService;

    /**
     * 报名课程
     */
    @PostMapping("/{courseId}")
    public ResponseEntity<ApiResponse<UserCourse>> enrollCourse(
            @PathVariable Long courseId,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }
            
            UserCourse userCourse = enrollmentService.enrollCourse(userId, courseId);
            return ResponseEntity.ok(ApiResponse.success("报名成功", userCourse));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 获取我的课程列表
     */
    @GetMapping("/my-courses")
    public ResponseEntity<ApiResponse<List<UserCourse>>> getMyCourses(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("请先登录"));
        }
        
        List<UserCourse> courses = enrollmentService.getStudentCourses(userId);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    /**
     * 检查是否已报名
     */
    @GetMapping("/check/{courseId}")
    public ResponseEntity<ApiResponse<Boolean>> checkEnrollment(
            @PathVariable Long courseId,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("请先登录"));
        }
        
        boolean enrolled = enrollmentService.isEnrolled(userId, courseId);
        return ResponseEntity.ok(ApiResponse.success(enrolled));
    }
}

