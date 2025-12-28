package com.teach.teachingsys.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphCourseRecommendationDto {
    private Long id;
    private String name;
    private Long frequency;
}

