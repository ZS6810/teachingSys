package com.teach.teachingsys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Data
@Entity
@Table(name = "exam", uniqueConstraints = {
        @UniqueConstraint(columnNames = "assignment_id", name = "uk_assignment")
})
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false, foreignKey = @ForeignKey(name = "exam_ibfk_1"))
    private Assignment assignment;

    @Column(name = "shuffle_questions")
    private Boolean shuffleQuestions = Boolean.TRUE;

    @Column(name = "show_correct_answer")
    private Boolean showCorrectAnswer = Boolean.FALSE;

    @Column(name = "allow_review")
    private Boolean allowReview = Boolean.TRUE;

    @Column(name = "settings", columnDefinition = "json")
    private String settings;
}

