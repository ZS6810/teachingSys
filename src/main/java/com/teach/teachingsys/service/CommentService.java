package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findByParentId(Long parentId);
    Page<Comment> findAll(Pageable pageable);
    void deleteById(Long id);
    Comment update(Comment comment);
}

