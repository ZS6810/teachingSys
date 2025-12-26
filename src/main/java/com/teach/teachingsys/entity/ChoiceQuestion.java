package com.teach.teachingsys.entity;

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

@Data
@Entity
@Table(name = "choice_question", uniqueConstraints = {
        @UniqueConstraint(columnNames = "question_id", name = "uk_question")
})
public class ChoiceQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "choice_question_ibfk_1"))
    private QuestionBank question;

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "option_e")
    private String optionE;

    @Column(name = "option_f")
    private String optionF;

    @Column(name = "correct_answer", length = 10, nullable = false)
    private String correctAnswer;

    @Column(name = "is_multiple")
    private Boolean isMultiple = Boolean.FALSE;
}

