package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.LearningProgress;
import com.teach.teachingsys.entity.enums.ProgressEnums.LearningStatus;
import com.teach.teachingsys.entity.enums.ProgressEnums.ProgressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearningProgressRepository extends JpaRepository<LearningProgress, Long> {
    @Query("SELECT lp FROM LearningProgress lp WHERE lp.user.id = :userId")
    List<LearningProgress> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT lp FROM LearningProgress lp WHERE lp.course.id = :courseId")
    List<LearningProgress> findByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT lp FROM LearningProgress lp WHERE lp.user.id = :userId AND lp.chapter.id = :chapterId")
    Optional<LearningProgress> findByUserIdAndChapterId(@Param("userId") Long userId, @Param("chapterId") Long chapterId);
    
    @Query("SELECT lp FROM LearningProgress lp WHERE lp.user.id = :userId AND lp.material.id = :materialId")
    Optional<LearningProgress> findByUserIdAndMaterialId(@Param("userId") Long userId, @Param("materialId") Long materialId);

    long countByUserIdAndCourseIdAndStatusAndProgressType(Long userId, Long courseId, LearningStatus status, ProgressType progressType);
}

