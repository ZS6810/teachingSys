package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    @Query("SELECT uc FROM UserCourse uc WHERE uc.user.id = :userId")
    List<UserCourse> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT uc FROM UserCourse uc WHERE uc.course.id = :courseId")
    List<UserCourse> findByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT uc FROM UserCourse uc WHERE uc.user.id = :userId AND uc.course.id = :courseId")
    Optional<UserCourse> findByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);
    
    @Query("SELECT COUNT(uc) > 0 FROM UserCourse uc WHERE uc.user.id = :userId AND uc.course.id = :courseId")
    boolean existsByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);
}

