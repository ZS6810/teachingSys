package com.teach.teachingsys.repository;

import com.teach.teachingsys.entity.ExamBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamBankRepository extends JpaRepository<ExamBank, Long> {
    @Query("SELECT eb FROM ExamBank eb WHERE eb.exam.id = :examId")
    List<ExamBank> findByExamId(@Param("examId") Long examId);
    
    @Query("SELECT eb FROM ExamBank eb WHERE eb.question.id = :questionId")
    List<ExamBank> findByQuestionId(@Param("questionId") Long questionId);
}

