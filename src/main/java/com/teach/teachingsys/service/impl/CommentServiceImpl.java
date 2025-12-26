package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Comment;
import com.teach.teachingsys.repository.CommentRepository;
import com.teach.teachingsys.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByParentId(Long parentId) {
        return commentRepository.findByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }
}

