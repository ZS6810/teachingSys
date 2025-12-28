package com.teach.teachingsys.graph.service;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.graph.dto.GraphCourseRecommendationDto;
import com.teach.teachingsys.graph.repository.GraphStudentRepository;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GraphRecommendationService {

    private final GraphStudentRepository graphStudentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    /**
     * 当 MySQL 选课成功后调用：异步把选课关系写入 Neo4j，避免阻塞主流程。
     *
     * <p>注意：这里刻意吞掉异常，确保 Neo4j 不可用时不影响 MySQL 主业务。</p>
     */
    @Async
    public void syncEnrollment(Long studentId, Long courseId) {
        try {
            User user = userRepository.findById(studentId).orElse(null);
            Course course = courseRepository.findById(courseId).orElse(null);
            if (user == null || course == null) {
                return;
            }

            String studentName = user.getRealName() != null ? user.getRealName() : user.getUsername();
            String courseName = course.getCourseName();

            graphStudentRepository.mergeEnrollment(studentId, studentName, courseId, courseName);
        } catch (Exception e) {
            log.warn("Neo4j syncEnrollment failed: studentId={}, courseId={}, error={}", studentId, courseId, e.getMessage());
        }
    }

    public List<GraphCourseRecommendationDto> getCourseRecommendations(Long studentId) {
        return graphStudentRepository.recommendCourses(studentId).stream()
                .map(v -> GraphCourseRecommendationDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .frequency(v.getFrequency())
                        .build())
                .toList();
    }
}

