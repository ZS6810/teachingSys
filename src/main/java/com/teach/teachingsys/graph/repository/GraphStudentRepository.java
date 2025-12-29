package com.teach.teachingsys.graph.repository;

import com.teach.teachingsys.graph.entity.GraphStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphStudentRepository extends Neo4jRepository<GraphStudent, Long> {

    @Query("""
            MERGE (s:GraphStudent {id: $studentId})
            SET s.name = $studentName
            MERGE (c:GraphCourse {id: $courseId})
            SET c.name = $courseName
            MERGE (s)-[:ENROLLED]->(c)
            """)
    void mergeEnrollment(
            @Param("studentId") Long studentId,
            @Param("studentName") String studentName,
            @Param("courseId") Long courseId,
            @Param("courseName") String courseName
    );

    /**
     * 猜你喜欢推荐算法
     * 这里的 AS id, AS name, AS frequency 会直接注入到 GraphCourseRecommendationView 类的对应字段中
     */
    @Query("""
            MATCH (me:GraphStudent {id: $studentId})-[:ENROLLED]->(common:GraphCourse)
            MATCH (other:GraphStudent)-[:ENROLLED]->(common)
            WHERE other.id <> $studentId
            MATCH (other)-[:ENROLLED]->(rec:GraphCourse)
            WHERE NOT (me)-[:ENROLLED]->(rec)
            RETURN rec.id AS id, rec.name AS name, count(DISTINCT other) AS frequency
            ORDER BY frequency DESC
            LIMIT 5
            """)
    List<GraphCourseRecommendationView> recommendCourses(@Param("studentId") Long studentId);
}