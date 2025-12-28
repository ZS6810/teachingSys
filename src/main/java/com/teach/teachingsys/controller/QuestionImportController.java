package com.teach.teachingsys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.dto.ImportQuestionsRequest;
import com.teach.teachingsys.dto.ImportQuestionsResponse;
import com.teach.teachingsys.service.QuestionImportService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionImportController {

    private final QuestionImportService questionImportService;

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<ImportQuestionsResponse>> importQuestions(
            @RequestBody ImportQuestionsRequest request,
            HttpSession session) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("请先登录"));
            }

            JsonNode questions = request.getQuestions();
            if (questions == null || !questions.isArray()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("questions 必须是 JSON 数组"));
            }

            ImportQuestionsResponse response = questionImportService.importQuestions(
                    request.getCourseId(),
                    request.getAssignmentId(),
                    questions.toString()
            );

            return ResponseEntity.ok(ApiResponse.success(response.getMessage(), response));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}

