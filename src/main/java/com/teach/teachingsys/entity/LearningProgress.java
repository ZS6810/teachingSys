package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teach.teachingsys.entity.enums.ProgressEnums.LearningStatus;
import com.teach.teachingsys.entity.enums.ProgressEnums.ProgressType;
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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "learningprogress", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "chapter_id"}, name = "uk_user_chapter"),
        @UniqueConstraint(columnNames = {"user_id", "material_id"}, name = "uk_user_material")
})
@SQLDelete(sql = "UPDATE learningprogress SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LearningProgress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "learningprogress_ibfk_1"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "learningprogress_ibfk_2"))
    private Course course;

    @Column(name = "course_name")
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", foreignKey = @ForeignKey(name = "learningprogress_ibfk_3"))
    private Chapter chapter;

    @Column(name = "chapter_name")
    private String chapterName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", foreignKey = @ForeignKey(name = "learningprogress_ibfk_4"))
    private CourseMaterial material;

    @Column(name = "material_name")
    private String materialName;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type")
    private CourseMaterial.MaterialType materialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_type", nullable = false)
    private ProgressType progressType;

    @Enumerated(EnumType.STRING)
    private LearningStatus status = LearningStatus.not_started;

    @Column(name = "completion_percentage", precision = 5, scale = 2)
    private BigDecimal completionPercentage = BigDecimal.ZERO;

    @Column(name = "video_watch_time")
    private Integer videoWatchTime = 0;

    @Column(name = "total_video_time")
    private Integer totalVideoTime = 0;

    @Column(name = "last_studied_time")
    private LocalDateTime lastStudiedTime;

    @Column(name = "completed_time")
    private LocalDateTime completedTime;

    private String notes;
}

