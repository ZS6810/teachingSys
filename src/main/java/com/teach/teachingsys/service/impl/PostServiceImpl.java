package com.teach.teachingsys.service.impl;

import com.teach.teachingsys.entity.Post;
import com.teach.teachingsys.repository.PostRepository;
import com.teach.teachingsys.service.PostService;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findByCourseId(Long courseId) {
        return postRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }
}

