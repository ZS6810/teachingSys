package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.AssignmentBank;

/**
 * 作业题库关系Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface AssignmentBankMapper 
{
    /**
     * 查询作业题库关系
     * 
     * @param id 作业题库关系主键
     * @return 作业题库关系
     */
    public AssignmentBank selectAssignmentBankById(Long id);

    /**
     * 查询作业题库关系列表
     * 
     * @param assignmentBank 作业题库关系
     * @return 作业题库关系集合
     */
    public List<AssignmentBank> selectAssignmentBankList(AssignmentBank assignmentBank);

    /**
     * 新增作业题库关系
     * 
     * @param assignmentBank 作业题库关系
     * @return 结果
     */
    public int insertAssignmentBank(AssignmentBank assignmentBank);

    /**
     * 修改作业题库关系
     * 
     * @param assignmentBank 作业题库关系
     * @return 结果
     */
    public int updateAssignmentBank(AssignmentBank assignmentBank);

    /**
     * 删除作业题库关系
     * 
     * @param id 作业题库关系主键
     * @return 结果
     */
    public int deleteAssignmentBankById(Long id);

    /**
     * 批量删除作业题库关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssignmentBankByIds(Long[] ids);
}
