package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseStatus;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.UserCourseRepository;
import com.teach.teachingsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseEnrollmentService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    /**
     * 学生报名课程
     */
    public UserCourse enrollCourse(Long userId, Long courseId) {
        // 检查用户是否存在
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        // 检查课程是否存在且已发布
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        if (course.getStatus() != CourseStatus.published) {
            throw new RuntimeException("课程未发布，无法报名");
        }
        
        // 检查是否已报名
        if (userCourseRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("您已经报名了该课程");
        }
        
        // 创建报名记录
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setEnrolledTime(LocalDateTime.now());
        userCourse.setStatus(UserCourse.CourseStudyStatus.active);
        
        userCourse = userCourseRepository.save(userCourse);
        
        // 更新课程的报名人数
        course.setTotalStudents(course.getTotalStudents() + 1);
        courseRepository.save(course);
        
        return userCourse;
    }

    /**
     * 获取学生的课程列表
     */
    @Transactional(readOnly = true)
    public List<UserCourse> getStudentCourses(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    /**
     * 检查学生是否已报名课程
     */
    @Transactional(readOnly = true)
    public boolean isEnrolled(Long userId, Long courseId) {
        return userCourseRepository.existsByUserIdAndCourseId(userId, courseId);
    }
}

