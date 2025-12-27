package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Chapter;
import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.CourseMaterial;
import com.teach.teachingsys.entity.LearningProgress;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.entity.UserCourse;
import com.teach.teachingsys.entity.enums.ProgressEnums.LearningStatus;
import com.teach.teachingsys.entity.enums.ProgressEnums.ProgressType;
import com.teach.teachingsys.repository.ChapterRepository;
import com.teach.teachingsys.repository.CourseMaterialRepository;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.LearningProgressRepository;
import com.teach.teachingsys.repository.UserCourseRepository;
import com.teach.teachingsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习进度业务服务
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LearningProgressBusinessService {

    private final LearningProgressRepository learningProgressRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final CourseMaterialRepository courseMaterialRepository;
    private final UserCourseRepository userCourseRepository;

    /**
     * 更新章节学习进度
     */
    public LearningProgress updateChapterProgress(Long userId, Long courseId, Long chapterId, 
                                                   BigDecimal completionPercentage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("章节不存在"));
        
        LearningProgress progress = learningProgressRepository.findByUserIdAndChapterId(userId, chapterId)
                .orElseGet(() -> {
                    LearningProgress newProgress = new LearningProgress();
                    newProgress.setUser(user);
                    newProgress.setCourse(course);
                    newProgress.setProgressType(ProgressType.chapter);
                    newProgress.setStatus(LearningStatus.in_progress);
                    return newProgress;
                });
        
        progress.setChapter(chapter);
        progress.setChapterName(chapter.getChapterName());
        progress.setCompletionPercentage(completionPercentage);
        progress.setLastStudiedTime(LocalDateTime.now());
        
        if (completionPercentage.compareTo(BigDecimal.valueOf(100)) >= 0) {
            progress.setStatus(LearningStatus.completed);
            progress.setCompletedTime(LocalDateTime.now());
        } else if (completionPercentage.compareTo(BigDecimal.ZERO) > 0) {
            progress.setStatus(LearningStatus.in_progress);
        }
        
        LearningProgress savedProgress = learningProgressRepository.save(progress);
        updateCourseProgress(userId, courseId);
        return savedProgress;
    }

    /**
     * 更新资料学习进度
     */
    public LearningProgress updateMaterialProgress(Long userId, Long courseId, Long materialId,
                                                     Integer watchTime, Integer totalTime) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        
        LearningProgress progress = learningProgressRepository.findByUserIdAndMaterialId(userId, materialId)
                .orElseGet(() -> {
                    LearningProgress newProgress = new LearningProgress();
                    newProgress.setUser(user);
                    newProgress.setCourse(course);
                    newProgress.setProgressType(ProgressType.material);
                    newProgress.setStatus(LearningStatus.in_progress);
                    return newProgress;
                });
        
        CourseMaterial material = courseMaterialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("资料不存在"));
        
        progress.setMaterial(material);
        progress.setMaterialName(material.getMaterialName());
        progress.setMaterialType(material.getMaterialType());
        progress.setVideoWatchTime(watchTime);
        progress.setTotalVideoTime(totalTime);
        progress.setLastStudiedTime(LocalDateTime.now());
        
        if (totalTime > 0) {
            BigDecimal percentage = BigDecimal.valueOf(watchTime)
                    .divide(BigDecimal.valueOf(totalTime), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            progress.setCompletionPercentage(percentage);
            
            if (percentage.compareTo(BigDecimal.valueOf(90)) >= 0) { // 观看90%以上视为完成
                progress.setStatus(LearningStatus.completed);
                progress.setCompletedTime(LocalDateTime.now());
            }
        }
        
        LearningProgress savedProgress = learningProgressRepository.save(progress);
        updateCourseProgress(userId, courseId);
        return savedProgress;
    }

    /**
     * 获取学生的学习进度
     */
    @Transactional(readOnly = true)
    public List<LearningProgress> getStudentProgress(Long userId, Long courseId) {
        if (courseId != null) {
            return learningProgressRepository.findByCourseId(courseId)
                    .stream()
                    .filter(p -> p.getUser().getId().equals(userId))
                    .toList();
        }
        return learningProgressRepository.findByUserId(userId);
    }

    private void updateCourseProgress(Long userId, Long courseId) {
        // 1. 获取课程资料总数
        long totalMaterials = courseMaterialRepository.countByChapter_Course_Id(courseId);

        // 2. 获取用户已完成的资料数 (状态为 completed)
        long completedMaterials = learningProgressRepository.countByUserIdAndCourseIdAndStatusAndProgressType(
                userId, courseId, LearningStatus.completed, ProgressType.material);

        // 3. 计算进度 (0.0 - 1.0)
        BigDecimal progressRate = totalMaterials == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(completedMaterials)
                .divide(BigDecimal.valueOf(totalMaterials), 2, RoundingMode.HALF_UP);

        // 4. 更新 UserCourse 表
        userCourseRepository.findByUserIdAndCourseId(userId, courseId).ifPresent(userCourse -> {
            userCourse.setProgressRate(progressRate);
            userCourse.setCompletedMaterials((int) completedMaterials);
            userCourse.setTotalMaterials((int) totalMaterials);
            userCourseRepository.save(userCourse);
        });
    }
}

