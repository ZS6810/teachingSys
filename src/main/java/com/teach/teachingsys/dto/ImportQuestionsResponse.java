package com.teach.teachingsys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportQuestionsResponse {
    private Integer importedCount;
    private Integer errorCount;
    private String message;
    private String errorDetails;
}

