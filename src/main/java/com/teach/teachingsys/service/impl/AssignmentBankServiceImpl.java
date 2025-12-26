package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.AssignmentBankMapper;
import com.teach.teachingsys.domain.AssignmentBank;
import com.teach.teachingsys.service.IAssignmentBankService;

/**
 * 作业题库关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class AssignmentBankServiceImpl implements IAssignmentBankService 
{
    @Autowired
    private AssignmentBankMapper assignmentBankMapper;

    /**
     * 查询作业题库关系
     * 
     * @param id 作业题库关系主键
     * @return 作业题库关系
     */
    @Override
    public AssignmentBank selectAssignmentBankById(Long id)
    {
        return assignmentBankMapper.selectAssignmentBankById(id);
    }

    /**
     * 查询作业题库关系列表
     * 
     * @param assignmentBank 作业题库关系
     * @return 作业题库关系
     */
    @Override
    public List<AssignmentBank> selectAssignmentBankList(AssignmentBank assignmentBank)
    {
        return assignmentBankMapper.selectAssignmentBankList(assignmentBank);
    }

    /**
     * 新增作业题库关系
     * 
     * @param assignmentBank 作业题库关系
     * @return 结果
     */
    @Override
    public int insertAssignmentBank(AssignmentBank assignmentBank)
    {
        return assignmentBankMapper.insertAssignmentBank(assignmentBank);
    }

    /**
     * 修改作业题库关系
     * 
     * @param assignmentBank 作业题库关系
     * @return 结果
     */
    @Override
    public int updateAssignmentBank(AssignmentBank assignmentBank)
    {
        return assignmentBankMapper.updateAssignmentBank(assignmentBank);
    }

    /**
     * 批量删除作业题库关系
     * 
     * @param ids 需要删除的作业题库关系主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentBankByIds(Long[] ids)
    {
        return assignmentBankMapper.deleteAssignmentBankByIds(ids);
    }

    /**
     * 删除作业题库关系信息
     * 
     * @param id 作业题库关系主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentBankById(Long id)
    {
        return assignmentBankMapper.deleteAssignmentBankById(id);
    }
}
