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
import com.teach.teachingsys.domain.Assignment;
import com.teach.teachingsys.service.IAssignmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/assignment")
public class AssignmentController extends BaseController
{
    @Autowired
    private IAssignmentService assignmentService;

    /**
     * 查询作业列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Assignment assignment)
    {
        startPage();
        List<Assignment> list = assignmentService.selectAssignmentList(assignment);
        return getDataTable(list);
    }

    /**
     * 导出作业列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:export')")
    @Log(title = "作业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Assignment assignment)
    {
        List<Assignment> list = assignmentService.selectAssignmentList(assignment);
        ExcelUtil<Assignment> util = new ExcelUtil<Assignment>(Assignment.class);
        util.exportExcel(response, list, "作业数据");
    }

    /**
     * 获取作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assignmentService.selectAssignmentById(id));
    }

    /**
     * 新增作业
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:add')")
    @Log(title = "作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Assignment assignment)
    {
        return toAjax(assignmentService.insertAssignment(assignment));
    }

    /**
     * 修改作业
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:edit')")
    @Log(title = "作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Assignment assignment)
    {
        return toAjax(assignmentService.updateAssignment(assignment));
    }

    /**
     * 删除作业
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignment:remove')")
    @Log(title = "作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assignmentService.deleteAssignmentByIds(ids));
    }
}
