package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.UserCourseMapper;
import com.teach.teachingsys.domain.UserCourse;
import com.teach.teachingsys.service.IUserCourseService;

/**
 * 用户课程关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class UserCourseServiceImpl implements IUserCourseService 
{
    @Autowired
    private UserCourseMapper userCourseMapper;

    /**
     * 查询用户课程关系
     * 
     * @param id 用户课程关系主键
     * @return 用户课程关系
     */
    @Override
    public UserCourse selectUserCourseById(Long id)
    {
        return userCourseMapper.selectUserCourseById(id);
    }

    /**
     * 查询用户课程关系列表
     * 
     * @param userCourse 用户课程关系
     * @return 用户课程关系
     */
    @Override
    public List<UserCourse> selectUserCourseList(UserCourse userCourse)
    {
        return userCourseMapper.selectUserCourseList(userCourse);
    }

    /**
     * 新增用户课程关系
     * 
     * @param userCourse 用户课程关系
     * @return 结果
     */
    @Override
    public int insertUserCourse(UserCourse userCourse)
    {
        return userCourseMapper.insertUserCourse(userCourse);
    }

    /**
     * 修改用户课程关系
     * 
     * @param userCourse 用户课程关系
     * @return 结果
     */
    @Override
    public int updateUserCourse(UserCourse userCourse)
    {
        return userCourseMapper.updateUserCourse(userCourse);
    }

    /**
     * 批量删除用户课程关系
     * 
     * @param ids 需要删除的用户课程关系主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseByIds(Long[] ids)
    {
        return userCourseMapper.deleteUserCourseByIds(ids);
    }

    /**
     * 删除用户课程关系信息
     * 
     * @param id 用户课程关系主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseById(Long id)
    {
        return userCourseMapper.deleteUserCourseById(id);
    }
}
