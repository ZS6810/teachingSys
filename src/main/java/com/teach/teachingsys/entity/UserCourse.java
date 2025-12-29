package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_course", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "course_id"}, name = "uk_user_course")
})
@SQLDelete(sql = "UPDATE user_course SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserCourse extends BaseEntity {

    public enum CourseStudyStatus {
        active, completed, dropped
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_course_ibfk_1"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "user_course_ibfk_2"))
    private Course course;

    @Column(name = "enrolled_time")
    private LocalDateTime enrolledTime;

    @Column(name = "progress_rate", precision = 5, scale = 2)
    private BigDecimal progressRate = BigDecimal.ZERO;

    @Column(name = "completed_chapters")
    private Integer completedChapters = 0;

    @Column(name = "total_chapters")
    private Integer totalChapters = 0;

    @Column(name = "completed_materials")
    private Integer completedMaterials = 0;

    @Column(name = "total_materials")
    private Integer totalMaterials = 0;

    @Column(name = "progress_updated_time")
    private LocalDateTime progressUpdatedTime;

    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;

    @Column(name = "completion_time")
    private LocalDateTime completionTime;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private CourseStudyStatus status = CourseStudyStatus.active;
}

