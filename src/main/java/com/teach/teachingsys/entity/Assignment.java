package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teach.teachingsys.entity.enums.AssignmentEnums.AssignmentStatus;
import com.teach.teachingsys.entity.enums.AssignmentEnums.AssignmentType;
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
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "assignment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "assignment_ibfk_1"))
    private Course course;

    @Column(nullable = false, length = 200)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_type", nullable = false)
    private AssignmentType assignmentType;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    private LocalDateTime deadline;

    @Column(name = "total_score", precision = 5, scale = 2)
    private BigDecimal totalScore = BigDecimal.valueOf(100.00);

    @Column(name = "passing_score", precision = 5, scale = 2)
    private BigDecimal passingScore = BigDecimal.valueOf(60.00);

    @Column(name = "time_limit")
    private Integer timeLimit;

    @Column(name = "max_attempts")
    private Integer maxAttempts = 1;

    @Column(name = "submission_count")
    private Integer submissionCount = 0;

    @Column(name = "graded_count")
    private Integer gradedCount = 0;

    @Column(name = "average_score", precision = 5, scale = 2)
    private BigDecimal averageScore = BigDecimal.ZERO;

    @Column(name = "highest_score", precision = 5, scale = 2)
    private BigDecimal highestScore = BigDecimal.ZERO;

    @Column(name = "lowest_score", precision = 5, scale = 2)
    private BigDecimal lowestScore = BigDecimal.ZERO;

    @Column(name = "stats_updated_time")
    private LocalDateTime statsUpdatedTime;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status = AssignmentStatus.draft;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

