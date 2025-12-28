package com.teach.teachingsys.graph.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("GraphStudent")
public class GraphStudent {

    @Id
    private Long id;

    private String name;

    @Builder.Default
    @Relationship(type = "ENROLLED")
    private Set<GraphCourse> enrolledCourses = new HashSet<>();
}

