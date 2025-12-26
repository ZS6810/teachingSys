package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findByCourseId(Long courseId);
    List<Post> findByUserId(Long userId);
    Page<Post> findAll(Pageable pageable);
    void deleteById(Long id);
    Post update(Post post);
}

