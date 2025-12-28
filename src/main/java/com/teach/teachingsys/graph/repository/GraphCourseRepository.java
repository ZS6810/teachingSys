package com.teach.teachingsys.graph.repository;

import com.teach.teachingsys.graph.entity.GraphCourse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphCourseRepository extends Neo4jRepository<GraphCourse, Long> {
}

