package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.User;
import com.teach.teachingsys.entity.enums.UserEnums.UserStatus;
import com.teach.teachingsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherApprovalService {

    private final UserRepository userRepository;

    /**
     * 获取待审核教师列表
     */
    @Transactional(readOnly = true)
    public List<User> getPendingTeachers() {
        return userRepository.findAll().stream()
                .filter(u -> u.getUserType() == com.teach.teachingsys.entity.enums.UserEnums.UserType.teacher
                        && u.getStatus() == UserStatus.pending)
                .toList();
    }

    /**
     * 审核通过教师
     */
    public User approveTeacher(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("教师不存在"));
        
        if (teacher.getUserType() != com.teach.teachingsys.entity.enums.UserEnums.UserType.teacher) {
            throw new RuntimeException("该用户不是教师");
        }
        
        teacher.setStatus(UserStatus.active);
        return userRepository.save(teacher);
    }

    /**
     * 拒绝教师申请
     */
    public User rejectTeacher(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("教师不存在"));
        
        if (teacher.getUserType() != com.teach.teachingsys.entity.enums.UserEnums.UserType.teacher) {
            throw new RuntimeException("该用户不是教师");
        }
        
        teacher.setStatus(UserStatus.inactive);
        return userRepository.save(teacher);
    }
}

