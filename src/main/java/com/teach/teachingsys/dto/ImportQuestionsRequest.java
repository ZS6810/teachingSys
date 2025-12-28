package com.teach.teachingsys.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportQuestionsRequest {
    private Long courseId;
    private Long assignmentId;
    private JsonNode questions;
}

