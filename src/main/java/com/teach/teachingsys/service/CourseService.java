package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findByTeacherId(Long teacherId);
    Page<Course> findAll(Pageable pageable);
    void deleteById(Long id);
    Course update(Course course);
}

