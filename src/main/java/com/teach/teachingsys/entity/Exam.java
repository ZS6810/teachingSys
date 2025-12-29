package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "exam", uniqueConstraints = {
        @UniqueConstraint(columnNames = "assignment_id", name = "uk_assignment")
})
@SQLDelete(sql = "UPDATE exam SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exam extends BaseEntity {

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

