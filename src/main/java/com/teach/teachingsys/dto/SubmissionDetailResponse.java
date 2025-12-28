package com.teach.teachingsys.dto;

import com.teach.teachingsys.entity.enums.QuestionEnums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDetailResponse {
    private Long submissionId;
    private Long assignmentId;
    private String assignmentTitle;
    private Long studentId;
    private String studentName;
    private BigDecimal totalScore; // Current score
    private BigDecimal maxScore; // Assignment total score
    private String feedback;
    private String status;
    private List<QuestionDetailResponse> questions;
}
