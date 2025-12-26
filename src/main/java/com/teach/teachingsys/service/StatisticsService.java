package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Assignment;
import com.teach.teachingsys.entity.AssignmentSubmission;
import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.repository.AssignmentRepository;
import com.teach.teachingsys.repository.AssignmentSubmissionRepository;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    /**
     * 课程报名统计
     */
    public Map<String, Object> getCourseEnrollmentStats(Long courseId) {
        Map<String, Object> stats = new HashMap<>();
        
        if (courseId != null) {
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course != null) {
                long enrollmentCount = userCourseRepository.findByCourseId(courseId).size();
                stats.put("courseId", courseId);
                stats.put("courseName", course.getCourseName());
                stats.put("enrollmentCount", enrollmentCount);
                stats.put("totalStudents", course.getTotalStudents());
            }
        } else {
            // 所有课程的统计
            List<Course> courses = courseRepository.findAll();
            long totalEnrollments = userCourseRepository.findAll().size();
            stats.put("totalCourses", courses.size());
            stats.put("totalEnrollments", totalEnrollments);
        }
        
        return stats;
    }

    /**
     * 课程收入统计
     */
    public Map<String, Object> getCourseRevenueStats(Long courseId, LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Object> stats = new HashMap<>();
        
        List<UserCourse> enrollments;
        if (courseId != null) {
            enrollments = userCourseRepository.findByCourseId(courseId);
        } else {
            enrollments = userCourseRepository.findAll();
        }
        
        // 过滤日期范围
        if (startDate != null || endDate != null) {
            enrollments = enrollments.stream()
                    .filter(uc -> {
                        if (uc.getEnrolledTime() == null) return false;
                        if (startDate != null && uc.getEnrolledTime().isBefore(startDate)) return false;
                        if (endDate != null && uc.getEnrolledTime().isAfter(endDate)) return false;
                        return true;
                    })
                    .toList();
        }
        
        BigDecimal totalRevenue = enrollments.stream()
                .map(uc -> uc.getCourse().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        stats.put("totalEnrollments", enrollments.size());
        stats.put("totalRevenue", totalRevenue);
        
        if (courseId != null) {
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course != null) {
                stats.put("courseName", course.getCourseName());
                stats.put("coursePrice", course.getPrice());
            }
        }
        
        return stats;
    }

    /**
     * 学生学习情况统计
     */
    public Map<String, Object> getStudentLearningStats(Long studentId, Long courseId) {
        Map<String, Object> stats = new HashMap<>();
        
        List<UserCourse> userCourses;
        if (studentId != null && courseId != null) {
            userCourses = userCourseRepository.findByUserIdAndCourseId(studentId, courseId)
                    .map(List::of)
                    .orElse(List.of());
        } else if (studentId != null) {
            userCourses = userCourseRepository.findByUserId(studentId);
        } else if (courseId != null) {
            userCourses = userCourseRepository.findByCourseId(courseId);
        } else {
            userCourses = userCourseRepository.findAll();
        }
        
        long totalCourses = userCourses.size();
        long completedCourses = userCourses.stream()
                .filter(uc -> uc.getStatus() == com.teach.teachingsys.entity.UserCourse.CourseStudyStatus.completed)
                .count();
        
        double avgProgress = userCourses.stream()
                .mapToDouble(uc -> uc.getProgressRate().doubleValue())
                .average()
                .orElse(0.0);
        
        stats.put("totalCourses", totalCourses);
        stats.put("completedCourses", completedCourses);
        stats.put("averageProgress", BigDecimal.valueOf(avgProgress).setScale(2, RoundingMode.HALF_UP));
        
        return stats;
    }

    /**
     * 作业成绩统计
     */
    public Map<String, Object> getAssignmentScoreStats(Long assignmentId) {
        Map<String, Object> stats = new HashMap<>();
        
        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);
        if (assignment == null) {
            return stats;
        }
        
        List<AssignmentSubmission> submissions = assignmentSubmissionRepository.findByAssignmentId(assignmentId);
        List<AssignmentSubmission> gradedSubmissions = submissions.stream()
                .filter(s -> s.getTotalScore() != null)
                .toList();
        
        if (!gradedSubmissions.isEmpty()) {
            double avgScore = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .average()
                    .orElse(0.0);
            
            double maxScore = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .max()
                    .orElse(0.0);
            
            double minScore = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .min()
                    .orElse(0.0);
            
            long passCount = gradedSubmissions.stream()
                    .filter(s -> assignment.getPassingScore() != null &&
                            s.getTotalScore().compareTo(assignment.getPassingScore()) >= 0)
                    .count();
            
            stats.put("assignmentId", assignmentId);
            stats.put("assignmentTitle", assignment.getTitle());
            stats.put("totalSubmissions", submissions.size());
            stats.put("gradedSubmissions", gradedSubmissions.size());
            stats.put("averageScore", BigDecimal.valueOf(avgScore).setScale(2, RoundingMode.HALF_UP));
            stats.put("maxScore", BigDecimal.valueOf(maxScore).setScale(2, RoundingMode.HALF_UP));
            stats.put("minScore", BigDecimal.valueOf(minScore).setScale(2, RoundingMode.HALF_UP));
            stats.put("passCount", passCount);
            stats.put("passRate", BigDecimal.valueOf((double) passCount / gradedSubmissions.size() * 100)
                    .setScale(2, RoundingMode.HALF_UP));
        } else {
            stats.put("assignmentId", assignmentId);
            stats.put("totalSubmissions", submissions.size());
            stats.put("gradedSubmissions", 0);
        }
        
        return stats;
    }

    /**
     * 课程综合统计（管理员/教师）
     */
    public Map<String, Object> getCourseComprehensiveStats(Long courseId) {
        Map<String, Object> stats = new HashMap<>();
        
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return stats;
        }
        
        // 报名统计
        long enrollmentCount = userCourseRepository.findByCourseId(courseId).size();
        
        // 作业统计
        List<Assignment> assignments = assignmentRepository.findByCourseId(courseId);
        long totalAssignments = assignments.size();
        long totalSubmissions = assignments.stream()
                .mapToLong(a -> assignmentSubmissionRepository.findByAssignmentId(a.getId()).size())
                .sum();
        
        // 收入统计
        BigDecimal totalRevenue = userCourseRepository.findByCourseId(courseId).stream()
                .map(uc -> course.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        stats.put("courseId", courseId);
        stats.put("courseName", course.getCourseName());
        stats.put("enrollmentCount", enrollmentCount);
        stats.put("totalAssignments", totalAssignments);
        stats.put("totalSubmissions", totalSubmissions);
        stats.put("totalRevenue", totalRevenue);
        stats.put("averageRating", course.getAverageRating());
        stats.put("reviewCount", course.getReviewCount());
        
        return stats;
    }
}

