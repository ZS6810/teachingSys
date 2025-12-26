package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserCourseService {
    UserCourse save(UserCourse userCourse);
    Optional<UserCourse> findById(Long id);
    List<UserCourse> findByUserId(Long userId);
    List<UserCourse> findByCourseId(Long courseId);
    Optional<UserCourse> findByUserIdAndCourseId(Long userId, Long courseId);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
    Page<UserCourse> findAll(Pageable pageable);
    void deleteById(Long id);
    UserCourse update(UserCourse userCourse);
}

