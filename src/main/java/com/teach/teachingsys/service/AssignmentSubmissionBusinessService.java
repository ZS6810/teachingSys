package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Assignment;
import com.teach.teachingsys.entity.AssignmentSubmission;
import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.entity.enums.AssignmentEnums.AssignmentStatus;
import com.teach.teachingsys.repository.AssignmentRepository;
import com.teach.teachingsys.repository.AssignmentSubmissionRepository;
import com.teach.teachingsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 作业提交业务服务
 * 注意：这个服务类与AssignmentSubmissionService接口不同，用于处理业务逻辑
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentSubmissionBusinessService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    /**
     * 提交作业
     */
    public AssignmentSubmission submitAssignment(Long assignmentId, Long userId, String submissionData) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("作业不存在"));
        
        if (assignment.getStatus() != AssignmentStatus.published) {
            throw new RuntimeException("作业未发布，无法提交");
        }
        
        // 检查截止时间
        if (assignment.getDeadline() != null && LocalDateTime.now().isAfter(assignment.getDeadline())) {
            throw new RuntimeException("作业已过期，无法提交");
        }
        
        // 获取当前尝试次数
        List<AssignmentSubmission> existingSubmissions = submissionRepository.findByAssignmentIdAndUserId(assignmentId, userId);
        int attemptNumber = existingSubmissions.size() + 1;
        
        // 检查最大尝试次数
        if (assignment.getMaxAttempts() != null && attemptNumber > assignment.getMaxAttempts()) {
            throw new RuntimeException("已达到最大尝试次数");
        }
        
        // 检查是否已存在相同尝试次数的提交
        submissionRepository.findByAssignmentIdAndUserIdAndAttemptNumber(assignmentId, userId, attemptNumber)
                .ifPresent(s -> {
                    throw new RuntimeException("该尝试次数的提交已存在");
                });
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        // 创建提交记录
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignment(assignment);
        submission.setStudent(user);
        submission.setAttemptNumber(attemptNumber);
        submission.setSubmittedTime(LocalDateTime.now());
        submission.setSubmissionData(submissionData);
        submission.setStatus(AssignmentSubmission.SubmissionStatus.submitted);
        
        submission = submissionRepository.save(submission);
        
        // 更新作业的提交次数
        assignment.setSubmissionCount(assignment.getSubmissionCount() + 1);
        assignmentRepository.save(assignment);
        
        return submission;
    }

    /**
     * 批改作业
     */
    public AssignmentSubmission gradeAssignment(Long submissionId, Long graderId, 
                                                 java.math.BigDecimal totalScore, 
                                                 java.math.BigDecimal autoGradedScore,
                                                 String feedback) {
        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("提交记录不存在"));
        
        User grader = userRepository.findById(graderId)
                .orElseThrow(() -> new RuntimeException("批改人不存在"));
        
        submission.setGrader(grader);
        submission.setTotalScore(totalScore);
        submission.setAutoGradedScore(autoGradedScore);
        submission.setTeacherFeedback(feedback);
        submission.setGradedTime(LocalDateTime.now());
        submission.setStatus(AssignmentSubmission.SubmissionStatus.graded);
        
        submission = submissionRepository.save(submission);
        
        // 更新作业统计信息
        Assignment assignment = submission.getAssignment();
        assignment.setGradedCount(assignment.getGradedCount() + 1);
        
        // 更新平均分、最高分、最低分
        List<AssignmentSubmission> gradedSubmissions = submissionRepository.findByAssignmentId(assignment.getId())
                .stream()
                .filter(s -> s.getTotalScore() != null)
                .toList();
        
        if (!gradedSubmissions.isEmpty()) {
            double avg = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .average()
                    .orElse(0.0);
            double max = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .max()
                    .orElse(0.0);
            double min = gradedSubmissions.stream()
                    .mapToDouble(s -> s.getTotalScore().doubleValue())
                    .min()
                    .orElse(0.0);
            
            assignment.setAverageScore(java.math.BigDecimal.valueOf(avg));
            assignment.setHighestScore(java.math.BigDecimal.valueOf(max));
            assignment.setLowestScore(java.math.BigDecimal.valueOf(min));
        }
        
        assignmentRepository.save(assignment);
        
        return submission;
    }

    /**
     * 获取学生的作业提交列表
     */
    @Transactional(readOnly = true)
    public List<AssignmentSubmission> getStudentSubmissions(Long userId, Long assignmentId) {
        if (assignmentId != null) {
            return submissionRepository.findByAssignmentIdAndUserId(assignmentId, userId);
        }
        return submissionRepository.findByUserId(userId);
    }

    /**
     * 获取作业的所有提交（教师查看）
     */
    @Transactional(readOnly = true)
    public List<AssignmentSubmission> getAssignmentSubmissions(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }
}

