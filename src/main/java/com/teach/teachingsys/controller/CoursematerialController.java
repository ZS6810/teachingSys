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
import com.teach.teachingsys.domain.Coursematerial;
import com.teach.teachingsys.service.ICoursematerialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程资料Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/coursematerial")
public class CoursematerialController extends BaseController
{
    @Autowired
    private ICoursematerialService coursematerialService;

    /**
     * 查询课程资料列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:list')")
    @GetMapping("/list")
    public TableDataInfo list(Coursematerial coursematerial)
    {
        startPage();
        List<Coursematerial> list = coursematerialService.selectCoursematerialList(coursematerial);
        return getDataTable(list);
    }

    /**
     * 导出课程资料列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:export')")
    @Log(title = "课程资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coursematerial coursematerial)
    {
        List<Coursematerial> list = coursematerialService.selectCoursematerialList(coursematerial);
        ExcelUtil<Coursematerial> util = new ExcelUtil<Coursematerial>(Coursematerial.class);
        util.exportExcel(response, list, "课程资料数据");
    }

    /**
     * 获取课程资料详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(coursematerialService.selectCoursematerialById(id));
    }

    /**
     * 新增课程资料
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:add')")
    @Log(title = "课程资料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coursematerial coursematerial)
    {
        return toAjax(coursematerialService.insertCoursematerial(coursematerial));
    }

    /**
     * 修改课程资料
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:edit')")
    @Log(title = "课程资料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coursematerial coursematerial)
    {
        return toAjax(coursematerialService.updateCoursematerial(coursematerial));
    }

    /**
     * 删除课程资料
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:coursematerial:remove')")
    @Log(title = "课程资料", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(coursematerialService.deleteCoursematerialByIds(ids));
    }
}
