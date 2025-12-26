package com.teach.teachingsys.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.teach.teachingsys.domain.UserRole;
import com.teach.teachingsys.service.IUserRoleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户角色关系Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/userRole")
public class UserRoleController extends BaseController
{
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 查询用户角色关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserRole userRole)
    {
        startPage();
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        return getDataTable(list);
    }

    /**
     * 导出用户角色关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:export')")
    @Log(title = "用户角色关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserRole userRole)
    {
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        ExcelUtil<UserRole> util = new ExcelUtil<UserRole>(UserRole.class);
        util.exportExcel(response, list, "用户角色关系数据");
    }

    /**
     * 获取用户角色关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userRoleService.selectUserRoleById(id));
    }

    /**
     * 新增用户角色关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:add')")
    @Log(title = "用户角色关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.insertUserRole(userRole));
    }

    /**
     * 修改用户角色关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:edit')")
    @Log(title = "用户角色关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.updateUserRole(userRole));
    }

    /**
     * 删除用户角色关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userRole:remove')")
    @Log(title = "用户角色关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userRoleService.deleteUserRoleByIds(ids));
    }
}
