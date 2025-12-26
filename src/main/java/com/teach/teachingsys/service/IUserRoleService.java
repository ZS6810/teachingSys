package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.UserRole;

/**
 * 用户角色关系Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IUserRoleService 
{
    /**
     * 查询用户角色关系
     * 
     * @param id 用户角色关系主键
     * @return 用户角色关系
     */
    public UserRole selectUserRoleById(Long id);

    /**
     * 查询用户角色关系列表
     * 
     * @param userRole 用户角色关系
     * @return 用户角色关系集合
     */
    public List<UserRole> selectUserRoleList(UserRole userRole);

    /**
     * 新增用户角色关系
     * 
     * @param userRole 用户角色关系
     * @return 结果
     */
    public int insertUserRole(UserRole userRole);

    /**
     * 修改用户角色关系
     * 
     * @param userRole 用户角色关系
     * @return 结果
     */
    public int updateUserRole(UserRole userRole);

    /**
     * 批量删除用户角色关系
     * 
     * @param ids 需要删除的用户角色关系主键集合
     * @return 结果
     */
    public int deleteUserRoleByIds(Long[] ids);

    /**
     * 删除用户角色关系信息
     * 
     * @param id 用户角色关系主键
     * @return 结果
     */
    public int deleteUserRoleById(Long id);
}
