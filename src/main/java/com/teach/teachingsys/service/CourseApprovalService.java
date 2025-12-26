package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseStatus;
import com.teach.teachingsys.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseApprovalService {

    private final CourseRepository courseRepository;

    /**
     * 提交课程审核
     */
    public Course submitForApproval(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        course.setStatus(CourseStatus.pending);
        return courseRepository.save(course);
    }

    /**
     * 审核通过课程
     */
    public Course approveCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        course.setStatus(CourseStatus.approved);
        course.setApprovalTime(LocalDateTime.now());
        return courseRepository.save(course);
    }

    /**
     * 发布课程
     */
    public Course publishCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        if (course.getStatus() != CourseStatus.approved) {
            throw new RuntimeException("课程必须先通过审核才能发布");
        }
        
        course.setStatus(CourseStatus.published);
        return courseRepository.save(course);
    }

    /**
     * 拒绝课程
     */
    public Course rejectCourse(Long courseId, String reason) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        course.setStatus(CourseStatus.rejected);
        course.setRejectionReason(reason);
        return courseRepository.save(course);
    }

    /**
     * 获取待审核课程列表
     */
    @Transactional(readOnly = true)
    public List<Course> getPendingCourses() {
        return courseRepository.findAll().stream()
                .filter(c -> c.getStatus() == CourseStatus.pending)
                .toList();
    }
}

