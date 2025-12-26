package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.RoleMapper;
import com.teach.teachingsys.domain.Role;
import com.teach.teachingsys.service.IRoleService;

/**
 * 角色Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    @Override
    public Role selectRoleById(Long id)
    {
        return roleMapper.selectRoleById(id);
    }

    /**
     * 查询角色列表
     * 
     * @param role 角色
     * @return 角色
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增角色
     * 
     * @param role 角色
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        return roleMapper.insertRole(role);
    }

    /**
     * 修改角色
     * 
     * @param role 角色
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色主键
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(Long[] ids)
    {
        return roleMapper.deleteRoleByIds(ids);
    }

    /**
     * 删除角色信息
     * 
     * @param id 角色主键
     * @return 结果
     */
    @Override
    public int deleteRoleById(Long id)
    {
        return roleMapper.deleteRoleById(id);
    }
}
