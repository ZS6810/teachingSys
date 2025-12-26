package com.teach.teachingsys.controller;

import com.teach.teachingsys.entity.Post;
import com.teach.teachingsys.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post saved = postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Post>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(postService.findByCourseId(courseId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.findByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        return postService.findById(id)
                .map(existing -> {
                    post.setId(id);
                    return ResponseEntity.ok(postService.update(post));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (postService.findById(id).isPresent()) {
            postService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

