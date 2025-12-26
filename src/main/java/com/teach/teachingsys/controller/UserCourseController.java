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
import com.teach.teachingsys.domain.UserCourse;
import com.teach.teachingsys.service.IUserCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户课程关系Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/userCourse")
public class UserCourseController extends BaseController
{
    @Autowired
    private IUserCourseService userCourseService;

    /**
     * 查询用户课程关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserCourse userCourse)
    {
        startPage();
        List<UserCourse> list = userCourseService.selectUserCourseList(userCourse);
        return getDataTable(list);
    }

    /**
     * 导出用户课程关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:export')")
    @Log(title = "用户课程关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserCourse userCourse)
    {
        List<UserCourse> list = userCourseService.selectUserCourseList(userCourse);
        ExcelUtil<UserCourse> util = new ExcelUtil<UserCourse>(UserCourse.class);
        util.exportExcel(response, list, "用户课程关系数据");
    }

    /**
     * 获取用户课程关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userCourseService.selectUserCourseById(id));
    }

    /**
     * 新增用户课程关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:add')")
    @Log(title = "用户课程关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCourse userCourse)
    {
        return toAjax(userCourseService.insertUserCourse(userCourse));
    }

    /**
     * 修改用户课程关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:edit')")
    @Log(title = "用户课程关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCourse userCourse)
    {
        return toAjax(userCourseService.updateUserCourse(userCourse));
    }

    /**
     * 删除用户课程关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:userCourse:remove')")
    @Log(title = "用户课程关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userCourseService.deleteUserCourseByIds(ids));
    }
}
