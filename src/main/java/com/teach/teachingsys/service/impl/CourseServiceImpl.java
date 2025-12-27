package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.UserRepository;
import com.teach.teachingsys.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Course save(Course course) {
        // 1. 获取完整的教师信息
        User teacher = userRepository.findById(course.getTeacher().getId())
                .orElseThrow(() -> new RuntimeException("教师不存在"));

        // 2. 将关联对象和冗余字段同步更新
        course.setTeacher(teacher);
        course.setTeacherName(teacher.getRealName()); // 将 User 的 realName 写入冗余列
        course.setTeacherAvatar(teacher.getAvatar()); // 将 User 的 avatar 写入冗余列

        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByTeacherId(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }
}

