package com.teach.teachingsys.controller;

import com.teach.teachingsys.dto.ApiResponse;
import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseLevel;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseStatus;
import com.teach.teachingsys.service.CourseSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/course-search")
@RequiredArgsConstructor
public class CourseSearchController {

    private final CourseSearchService searchService;

    /**
     * 搜索课程
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Course>>> searchCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) CourseLevel level,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) CourseStatus status,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "createdTime") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("ASC") 
                ? Sort.by(sortBy).ascending() 
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Course> courses = searchService.searchCourses(
                keyword, category, level, minPrice, maxPrice, status, pageable);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    /**
     * 按分类获取课程
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<Page<Course>>> getCoursesByCategory(
            @PathVariable String category,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Course> courses = searchService.getCoursesByCategory(category, pageable);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    /**
     * 按教师获取课程
     */
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ApiResponse<Page<Course>>> getCoursesByTeacher(
            @PathVariable Long teacherId,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Course> courses = searchService.getCoursesByTeacher(teacherId, pageable);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    /**
     * 获取热门课程
     */
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<Page<Course>>> getPopularCourses(
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Course> courses = searchService.getPopularCourses(pageable);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }
}

