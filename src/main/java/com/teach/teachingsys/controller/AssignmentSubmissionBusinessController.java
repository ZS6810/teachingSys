package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.AssignmentSubmission;
import com.teach.teachingsys.service.AssignmentSubmissionBusinessService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignment-submissions/business")
@RequiredArgsConstructor
public class AssignmentSubmissionBusinessController {

    private final AssignmentSubmissionBusinessService submissionBusinessService;

    /**
     * 提交作业（学生）
     */
    @PostMapping("/{assignmentId}/submit")
    public ResponseEntity<ApiResponse<AssignmentSubmission>> submitAssignment(
            @PathVariable Long assignmentId,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }
            
            String submissionData = request.get("submissionData");
            AssignmentSubmission submission = submissionBusinessService.submitAssignment(
                    assignmentId, userId, submissionData);
            return ResponseEntity.ok(ApiResponse.success("提交成功", submission));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 批改作业（教师）
     */
    @PostMapping("/{submissionId}/grade")
    public ResponseEntity<ApiResponse<AssignmentSubmission>> gradeAssignment(
            @PathVariable Long submissionId,
            @RequestBody Map<String, Object> request,
            HttpSession session) {
        try {
            Long graderId = (Long) session.getAttribute("userId");
            if (graderId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }
            
            BigDecimal totalScore = new BigDecimal(request.get("totalScore").toString());
            BigDecimal autoGradedScore = request.get("autoGradedScore") != null 
                    ? new BigDecimal(request.get("autoGradedScore").toString()) 
                    : BigDecimal.ZERO;
            String feedback = (String) request.get("feedback");
            
            AssignmentSubmission submission = submissionBusinessService.gradeAssignment(
                    submissionId, graderId, totalScore, autoGradedScore, feedback);
            return ResponseEntity.ok(ApiResponse.success("批改成功", submission));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 获取我的作业提交列表（学生）
     */
    @GetMapping("/my-submissions")
    public ResponseEntity<ApiResponse<List<AssignmentSubmission>>> getMySubmissions(
            @RequestParam(required = false) Long assignmentId,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("请先登录"));
        }
        
        List<AssignmentSubmission> submissions = submissionBusinessService.getStudentSubmissions(userId, assignmentId);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }

    /**
     * 获取作业的所有提交（教师）
     */
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<List<AssignmentSubmission>>> getAssignmentSubmissions(
            @PathVariable Long assignmentId) {
        List<AssignmentSubmission> submissions = submissionBusinessService.getAssignmentSubmissions(assignmentId);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }
}

