package com.teach.teachingsys.dto;

import com.teach.teachingsys.entity.enums.QuestionEnums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailResponse {
    private Long questionId;
    private String questionText;
    private QuestionType questionType;
    private BigDecimal score; // Max score for this question
    
    // Choice Question specific
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private Boolean isMultiple;
    
    // Student's answer
    private String studentAnswer;
    
    // Correct/Reference Answer (for teacher)
    private String correctAnswer;
    private String explanation;
}
