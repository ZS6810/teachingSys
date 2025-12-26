package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Coursematerial;

/**
 * 课程资料Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface ICoursematerialService 
{
    /**
     * 查询课程资料
     * 
     * @param id 课程资料主键
     * @return 课程资料
     */
    public Coursematerial selectCoursematerialById(Long id);

    /**
     * 查询课程资料列表
     * 
     * @param coursematerial 课程资料
     * @return 课程资料集合
     */
    public List<Coursematerial> selectCoursematerialList(Coursematerial coursematerial);

    /**
     * 新增课程资料
     * 
     * @param coursematerial 课程资料
     * @return 结果
     */
    public int insertCoursematerial(Coursematerial coursematerial);

    /**
     * 修改课程资料
     * 
     * @param coursematerial 课程资料
     * @return 结果
     */
    public int updateCoursematerial(Coursematerial coursematerial);

    /**
     * 批量删除课程资料
     * 
     * @param ids 需要删除的课程资料主键集合
     * @return 结果
     */
    public int deleteCoursematerialByIds(Long[] ids);

    /**
     * 删除课程资料信息
     * 
     * @param id 课程资料主键
     * @return 结果
     */
    public int deleteCoursematerialById(Long id);
}
