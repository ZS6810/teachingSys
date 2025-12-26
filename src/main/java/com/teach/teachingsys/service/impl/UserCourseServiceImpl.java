package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.repository.UserCourseRepository;
import com.teach.teachingsys.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;

    @Override
    public UserCourse save(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserCourse> findById(Long id) {
        return userCourseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserCourse> findByUserId(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserCourse> findByCourseId(Long courseId) {
        return userCourseRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserCourse> findByUserIdAndCourseId(Long userId, Long courseId) {
        return userCourseRepository.findByUserIdAndCourseId(userId, courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserIdAndCourseId(Long userId, Long courseId) {
        return userCourseRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserCourse> findAll(Pageable pageable) {
        return userCourseRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        userCourseRepository.deleteById(id);
    }

    @Override
    public UserCourse update(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }
}

