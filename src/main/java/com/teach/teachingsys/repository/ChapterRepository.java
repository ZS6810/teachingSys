package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    @Query("SELECT ch FROM Chapter ch WHERE ch.course.id = :courseId")
    List<Chapter> findByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT ch FROM Chapter ch WHERE ch.parent.id = :parentId")
    List<Chapter> findByParentId(@Param("parentId") Long parentId);
}

