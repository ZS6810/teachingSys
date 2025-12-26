package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseId")
    List<Assignment> findByCourseId(@Param("courseId") Long courseId);
}

