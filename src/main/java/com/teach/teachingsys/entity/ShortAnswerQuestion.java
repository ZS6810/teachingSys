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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "short_answer_question", uniqueConstraints = {
        @UniqueConstraint(columnNames = "question_id", name = "uk_question")
})
@SQLDelete(sql = "UPDATE short_answer_question SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShortAnswerQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "short_answer_question_ibfk_1"))
    private QuestionBank question;

    @Column(name = "reference_answer")
    private String referenceAnswer;

    @Column(name = "answer_length_limit")
    private Integer answerLengthLimit;
}

