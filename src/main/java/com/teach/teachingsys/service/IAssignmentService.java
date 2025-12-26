package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Assignment;

/**
 * 作业Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IAssignmentService 
{
    /**
     * 查询作业
     * 
     * @param id 作业主键
     * @return 作业
     */
    public Assignment selectAssignmentById(Long id);

    /**
     * 查询作业列表
     * 
     * @param assignment 作业
     * @return 作业集合
     */
    public List<Assignment> selectAssignmentList(Assignment assignment);

    /**
     * 新增作业
     * 
     * @param assignment 作业
     * @return 结果
     */
    public int insertAssignment(Assignment assignment);

    /**
     * 修改作业
     * 
     * @param assignment 作业
     * @return 结果
     */
    public int updateAssignment(Assignment assignment);

    /**
     * 批量删除作业
     * 
     * @param ids 需要删除的作业主键集合
     * @return 结果
     */
    public int deleteAssignmentByIds(Long[] ids);

    /**
     * 删除作业信息
     * 
     * @param id 作业主键
     * @return 结果
     */
    public int deleteAssignmentById(Long id);
}
