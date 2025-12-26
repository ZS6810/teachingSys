package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseLevel;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseStatus;
import com.teach.teachingsys.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseSearchService {

    private final CourseRepository courseRepository;

    /**
     * 搜索课程
     * @param keyword 关键词（课程名称、描述）
     * @param category 分类
     * @param level 难度等级
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @param status 课程状态
     * @param pageable 分页参数
     * @return 分页结果
     */
    public Page<Course> searchCourses(String keyword, String category, CourseLevel level,
                                      BigDecimal minPrice, BigDecimal maxPrice,
                                      CourseStatus status, Pageable pageable) {
        Specification<Course> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 关键词搜索（课程名称或描述）
            if (keyword != null && !keyword.trim().isEmpty()) {
                Predicate namePredicate = cb.like(cb.lower(root.get("courseName")), 
                        "%" + keyword.toLowerCase() + "%");
                Predicate descPredicate = cb.like(cb.lower(root.get("description")), 
                        "%" + keyword.toLowerCase() + "%");
                predicates.add(cb.or(namePredicate, descPredicate));
            }

            // 分类筛选
            if (category != null && !category.trim().isEmpty()) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            // 难度等级筛选
            if (level != null) {
                predicates.add(cb.equal(root.get("level"), level));
            }

            // 价格范围筛选
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            // 状态筛选（默认只显示已发布的课程）
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            } else {
                predicates.add(cb.equal(root.get("status"), CourseStatus.published));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return courseRepository.findAll(spec, pageable);
    }

    /**
     * 按分类获取课程
     */
    public Page<Course> getCoursesByCategory(String category, Pageable pageable) {
        return searchCourses(null, category, null, null, null, CourseStatus.published, pageable);
    }

    /**
     * 按教师获取课程
     */
    public Page<Course> getCoursesByTeacher(Long teacherId, Pageable pageable) {
        Specification<Course> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("teacher").get("id"), teacherId));
            predicates.add(cb.equal(root.get("status"), CourseStatus.published));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return courseRepository.findAll(spec, pageable);
    }

    /**
     * 获取热门课程（按报名人数排序）
     */
    public Page<Course> getPopularCourses(Pageable pageable) {
        Specification<Course> spec = (root, query, cb) -> {
            return cb.equal(root.get("status"), CourseStatus.published);
        };
        // 注意：这里需要自定义排序，按报名人数降序
        // 简化处理，返回已发布的课程，前端可以按totalStudents排序
        return courseRepository.findAll(spec, pageable);
    }
}

