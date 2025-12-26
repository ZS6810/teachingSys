package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.UserRoleMapper;
import com.teach.teachingsys.domain.UserRole;
import com.teach.teachingsys.service.IUserRoleService;

/**
 * 用户角色关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService 
{
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询用户角色关系
     * 
     * @param id 用户角色关系主键
     * @return 用户角色关系
     */
    @Override
    public UserRole selectUserRoleById(Long id)
    {
        return userRoleMapper.selectUserRoleById(id);
    }

    /**
     * 查询用户角色关系列表
     * 
     * @param userRole 用户角色关系
     * @return 用户角色关系
     */
    @Override
    public List<UserRole> selectUserRoleList(UserRole userRole)
    {
        return userRoleMapper.selectUserRoleList(userRole);
    }

    /**
     * 新增用户角色关系
     * 
     * @param userRole 用户角色关系
     * @return 结果
     */
    @Override
    public int insertUserRole(UserRole userRole)
    {
        return userRoleMapper.insertUserRole(userRole);
    }

    /**
     * 修改用户角色关系
     * 
     * @param userRole 用户角色关系
     * @return 结果
     */
    @Override
    public int updateUserRole(UserRole userRole)
    {
        return userRoleMapper.updateUserRole(userRole);
    }

    /**
     * 批量删除用户角色关系
     * 
     * @param ids 需要删除的用户角色关系主键
     * @return 结果
     */
    @Override
    public int deleteUserRoleByIds(Long[] ids)
    {
        return userRoleMapper.deleteUserRoleByIds(ids);
    }

    /**
     * 删除用户角色关系信息
     * 
     * @param id 用户角色关系主键
     * @return 结果
     */
    @Override
    public int deleteUserRoleById(Long id)
    {
        return userRoleMapper.deleteUserRoleById(id);
    }
}
