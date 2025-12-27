package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
    @Query("SELECT cm FROM CourseMaterial cm WHERE cm.chapter.id = :chapterId")
    List<CourseMaterial> findByChapterId(@Param("chapterId") Long chapterId);

    long countByChapter_Course_Id(Long courseId);
}

