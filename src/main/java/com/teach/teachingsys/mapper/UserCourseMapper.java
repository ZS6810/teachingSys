package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.UserCourse;

/**
 * 用户课程关系Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface UserCourseMapper 
{
    /**
     * 查询用户课程关系
     * 
     * @param id 用户课程关系主键
     * @return 用户课程关系
     */
    public UserCourse selectUserCourseById(Long id);

    /**
     * 查询用户课程关系列表
     * 
     * @param userCourse 用户课程关系
     * @return 用户课程关系集合
     */
    public List<UserCourse> selectUserCourseList(UserCourse userCourse);

    /**
     * 新增用户课程关系
     * 
     * @param userCourse 用户课程关系
     * @return 结果
     */
    public int insertUserCourse(UserCourse userCourse);

    /**
     * 修改用户课程关系
     * 
     * @param userCourse 用户课程关系
     * @return 结果
     */
    public int updateUserCourse(UserCourse userCourse);

    /**
     * 删除用户课程关系
     * 
     * @param id 用户课程关系主键
     * @return 结果
     */
    public int deleteUserCourseById(Long id);

    /**
     * 批量删除用户课程关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCourseByIds(Long[] ids);
}
