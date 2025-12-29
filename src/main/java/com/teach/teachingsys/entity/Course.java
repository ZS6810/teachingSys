package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseLevel;
import com.teach.teachingsys.entity.enums.CourseEnums.CourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false, foreignKey = @ForeignKey(name = "course_ibfk_1"))
    private User teacher;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_avatar")
    private String teacherAvatar;

    @Column(name = "course_name", nullable = false, length = 200)
    private String courseName;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    private String category;

    @Column(name = "cover_image")
    private String coverImage;

    @Enumerated(EnumType.STRING)
    private CourseStatus status = CourseStatus.draft;

    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    @Column(name = "total_students")
    private Integer totalStudents = 0;

    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Column(name = "stats_updated_time")
    private LocalDateTime statsUpdatedTime;

    @Column(name = "approval_time")
    private LocalDateTime approvalTime;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

