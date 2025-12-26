package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.CoursematerialMapper;
import com.teach.teachingsys.domain.Coursematerial;
import com.teach.teachingsys.service.ICoursematerialService;

/**
 * 课程资料Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class CoursematerialServiceImpl implements ICoursematerialService 
{
    @Autowired
    private CoursematerialMapper coursematerialMapper;

    /**
     * 查询课程资料
     * 
     * @param id 课程资料主键
     * @return 课程资料
     */
    @Override
    public Coursematerial selectCoursematerialById(Long id)
    {
        return coursematerialMapper.selectCoursematerialById(id);
    }

    /**
     * 查询课程资料列表
     * 
     * @param coursematerial 课程资料
     * @return 课程资料
     */
    @Override
    public List<Coursematerial> selectCoursematerialList(Coursematerial coursematerial)
    {
        return coursematerialMapper.selectCoursematerialList(coursematerial);
    }

    /**
     * 新增课程资料
     * 
     * @param coursematerial 课程资料
     * @return 结果
     */
    @Override
    public int insertCoursematerial(Coursematerial coursematerial)
    {
        return coursematerialMapper.insertCoursematerial(coursematerial);
    }

    /**
     * 修改课程资料
     * 
     * @param coursematerial 课程资料
     * @return 结果
     */
    @Override
    public int updateCoursematerial(Coursematerial coursematerial)
    {
        return coursematerialMapper.updateCoursematerial(coursematerial);
    }

    /**
     * 批量删除课程资料
     * 
     * @param ids 需要删除的课程资料主键
     * @return 结果
     */
    @Override
    public int deleteCoursematerialByIds(Long[] ids)
    {
        return coursematerialMapper.deleteCoursematerialByIds(ids);
    }

    /**
     * 删除课程资料信息
     * 
     * @param id 课程资料主键
     * @return 结果
     */
    @Override
    public int deleteCoursematerialById(Long id)
    {
        return coursematerialMapper.deleteCoursematerialById(id);
    }
}
