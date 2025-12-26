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
import com.teach.teachingsys.domain.AssignmentSubmission;
import com.teach.teachingsys.service.IAssignmentSubmissionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业提交Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/assignmentSubmission")
public class AssignmentSubmissionController extends BaseController
{
    @Autowired
    private IAssignmentSubmissionService assignmentSubmissionService;

    /**
     * 查询作业提交列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssignmentSubmission assignmentSubmission)
    {
        startPage();
        List<AssignmentSubmission> list = assignmentSubmissionService.selectAssignmentSubmissionList(assignmentSubmission);
        return getDataTable(list);
    }

    /**
     * 导出作业提交列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:export')")
    @Log(title = "作业提交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssignmentSubmission assignmentSubmission)
    {
        List<AssignmentSubmission> list = assignmentSubmissionService.selectAssignmentSubmissionList(assignmentSubmission);
        ExcelUtil<AssignmentSubmission> util = new ExcelUtil<AssignmentSubmission>(AssignmentSubmission.class);
        util.exportExcel(response, list, "作业提交数据");
    }

    /**
     * 获取作业提交详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assignmentSubmissionService.selectAssignmentSubmissionById(id));
    }

    /**
     * 新增作业提交
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:add')")
    @Log(title = "作业提交", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssignmentSubmission assignmentSubmission)
    {
        return toAjax(assignmentSubmissionService.insertAssignmentSubmission(assignmentSubmission));
    }

    /**
     * 修改作业提交
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:edit')")
    @Log(title = "作业提交", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssignmentSubmission assignmentSubmission)
    {
        return toAjax(assignmentSubmissionService.updateAssignmentSubmission(assignmentSubmission));
    }

    /**
     * 删除作业提交
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentSubmission:remove')")
    @Log(title = "作业提交", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assignmentSubmissionService.deleteAssignmentSubmissionByIds(ids));
    }
}
