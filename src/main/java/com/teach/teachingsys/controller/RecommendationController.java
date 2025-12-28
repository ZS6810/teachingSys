package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.graph.dto.GraphCourseRecommendationDto;
import com.teach.teachingsys.graph.service.GraphRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final GraphRecommendationService graphRecommendationService;

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<List<GraphCourseRecommendationDto>>> getRecommendations(@PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success(graphRecommendationService.getCourseRecommendations(studentId)));
    }
}

