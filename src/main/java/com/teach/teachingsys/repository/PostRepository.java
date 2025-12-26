package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.course.id = :courseId")
    List<Post> findByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT p FROM Post p WHERE p.author.id = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);
}

