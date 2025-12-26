package com.teach.teachingsys.entity;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "assignment_submission", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"assignment_id", "user_id", "attempt_number"}, name = "uk_assignment_user_attempt")
})
public class AssignmentSubmission {

    public enum SubmissionStatus {
        submitted, graded, returned
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false, foreignKey = @ForeignKey(name = "assignment_submission_ibfk_1"))
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "assignment_submission_ibfk_2"))
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grader_id", foreignKey = @ForeignKey(name = "assignment_submission_ibfk_3"))
    private User grader;

    @Column(name = "attempt_number")
    private Integer attemptNumber = 1;

    @Column(name = "submitted_time")
    private LocalDateTime submittedTime;

    @Column(name = "total_score", precision = 5, scale = 2)
    private BigDecimal totalScore = BigDecimal.ZERO;

    @Column(name = "auto_graded_score", precision = 5, scale = 2)
    private BigDecimal autoGradedScore = BigDecimal.ZERO;

    @Column(name = "teacher_feedback")
    private String teacherFeedback;

    @Column(name = "graded_time")
    private LocalDateTime gradedTime;

    @Column(name = "submission_data", columnDefinition = "json")
    private String submissionData;

    @Column(name = "has_question_snapshot")
    private Boolean hasQuestionSnapshot = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status = SubmissionStatus.submitted;
}

