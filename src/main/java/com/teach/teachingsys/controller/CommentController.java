package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Comment;
import com.teach.teachingsys.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        Comment saved = commentService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(@PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.findByUserId(userId));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<Comment>> getByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(commentService.findByParentId(parentId));
    }

    @GetMapping
    public ResponseEntity<Page<Comment>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(commentService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.findById(id)
                .map(existing -> {
                    comment.setId(id);
                    return ResponseEntity.ok(commentService.update(comment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (commentService.findById(id).isPresent()) {
            commentService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

