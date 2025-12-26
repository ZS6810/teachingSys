package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.LearningProgress;
import com.teach.teachingsys.service.LearningProgressBusinessService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/learning-progress")
@RequiredArgsConstructor
public class LearningProgressBusinessController {

    private final LearningProgressBusinessService progressBusinessService;

    /**
     * 更新章节学习进度
     */
    @PostMapping("/chapter")
    public ResponseEntity<ApiResponse<LearningProgress>> updateChapterProgress(
            @RequestBody Map<String, Object> request,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }
            
            Long courseId = Long.valueOf(request.get("courseId").toString());
            Long chapterId = Long.valueOf(request.get("chapterId").toString());
            BigDecimal completionPercentage = new BigDecimal(request.get("completionPercentage").toString());
            
            LearningProgress progress = progressBusinessService.updateChapterProgress(
                    userId, courseId, chapterId, completionPercentage);
            return ResponseEntity.ok(ApiResponse.success("进度更新成功", progress));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 更新资料学习进度
     */
    @PostMapping("/material")
    public ResponseEntity<ApiResponse<LearningProgress>> updateMaterialProgress(
            @RequestBody Map<String, Object> request,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }
            
            Long courseId = Long.valueOf(request.get("courseId").toString());
            Long materialId = Long.valueOf(request.get("materialId").toString());
            Integer watchTime = Integer.valueOf(request.get("watchTime").toString());
            Integer totalTime = Integer.valueOf(request.get("totalTime").toString());
            
            LearningProgress progress = progressBusinessService.updateMaterialProgress(
                    userId, courseId, materialId, watchTime, totalTime);
            return ResponseEntity.ok(ApiResponse.success("进度更新成功", progress));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * 获取学习进度
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<LearningProgress>>> getProgress(
            @RequestParam(required = false) Long courseId,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("请先登录"));
        }
        
        List<LearningProgress> progressList = progressBusinessService.getStudentProgress(userId, courseId);
        return ResponseEntity.ok(ApiResponse.success(progressList));
    }
}

