package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "exam_bank", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"exam_id", "question_id"}, name = "uk_exam_question")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ExamBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id", nullable = false, foreignKey = @ForeignKey(name = "exam_bank_ibfk_1"))
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "exam_bank_ibfk_2"))
    private QuestionBank question;

    private Integer questionOrder;
}

