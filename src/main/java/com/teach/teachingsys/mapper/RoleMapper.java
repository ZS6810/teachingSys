package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.Role;

/**
 * 角色Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface RoleMapper 
{
    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    public Role selectRoleById(Long id);

    /**
     * 查询角色列表
     * 
     * @param role 角色
     * @return 角色集合
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 新增角色
     * 
     * @param role 角色
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改角色
     * 
     * @param role 角色
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 删除角色
     * 
     * @param id 角色主键
     * @return 结果
     */
    public int deleteRoleById(Long id);

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoleByIds(Long[] ids);
}
