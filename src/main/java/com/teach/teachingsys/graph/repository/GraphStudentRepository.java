package com.teach.teachingsys.graph.repository;

import com.teach.teachingsys.graph.entity.GraphStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphStudentRepository extends Neo4jRepository<GraphStudent, Long> {

    /**
     * 将 MySQL 的“选课成功”同步到 Neo4j：
     * 1) MERGE 学生节点（GraphStudent）
     * 2) MERGE 课程节点（GraphCourse）
     * 3) MERGE 选课关系：(:GraphStudent)-[:ENROLLED]->(:GraphCourse)
     */
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
     * “猜你喜欢（协同过滤）”推荐查询（Top 5）。
     *
     * <p>Cypher 逻辑解释（可直接写入报告）：</p>
     * <ol>
     *   <li>找到当前学生 me</li>
     *   <li>找到 me 选修的所有课程（common）</li>
     *   <li>找到也选修了 common 的其他学生 other（协同用户）</li>
     *   <li>找到 other 选修、但 me 未选修的课程 rec（候选推荐）</li>
     *   <li>按“有多少个 other 选过 rec”作为频率进行统计，降序取前 5</li>
     * </ol>
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

