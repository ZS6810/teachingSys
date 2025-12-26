package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    /**
     * 课程报名统计
     */
    @GetMapping("/enrollment")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getEnrollmentStats(
            @RequestParam(required = false) Long courseId) {
        Map<String, Object> stats = statisticsService.getCourseEnrollmentStats(courseId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 课程收入统计
     */
    @GetMapping("/revenue")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRevenueStats(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Map<String, Object> stats = statisticsService.getCourseRevenueStats(courseId, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 学生学习情况统计
     */
    @GetMapping("/learning")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getLearningStats(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long courseId) {
        Map<String, Object> stats = statisticsService.getStudentLearningStats(studentId, courseId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 作业成绩统计
     */
    @GetMapping("/assignment/{assignmentId}/scores")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAssignmentScoreStats(
            @PathVariable Long assignmentId) {
        Map<String, Object> stats = statisticsService.getAssignmentScoreStats(assignmentId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 课程综合统计
     */
    @GetMapping("/course/{courseId}/comprehensive")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCourseComprehensiveStats(
            @PathVariable Long courseId) {
        Map<String, Object> stats = statisticsService.getCourseComprehensiveStats(courseId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }
}

