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
import com.teach.teachingsys.domain.AssignmentBank;
import com.teach.teachingsys.service.IAssignmentBankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业题库关系Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/assignmentBank")
public class AssignmentBankController extends BaseController
{
    @Autowired
    private IAssignmentBankService assignmentBankService;

    /**
     * 查询作业题库关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssignmentBank assignmentBank)
    {
        startPage();
        List<AssignmentBank> list = assignmentBankService.selectAssignmentBankList(assignmentBank);
        return getDataTable(list);
    }

    /**
     * 导出作业题库关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:export')")
    @Log(title = "作业题库关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssignmentBank assignmentBank)
    {
        List<AssignmentBank> list = assignmentBankService.selectAssignmentBankList(assignmentBank);
        ExcelUtil<AssignmentBank> util = new ExcelUtil<AssignmentBank>(AssignmentBank.class);
        util.exportExcel(response, list, "作业题库关系数据");
    }

    /**
     * 获取作业题库关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assignmentBankService.selectAssignmentBankById(id));
    }

    /**
     * 新增作业题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:add')")
    @Log(title = "作业题库关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssignmentBank assignmentBank)
    {
        return toAjax(assignmentBankService.insertAssignmentBank(assignmentBank));
    }

    /**
     * 修改作业题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:edit')")
    @Log(title = "作业题库关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssignmentBank assignmentBank)
    {
        return toAjax(assignmentBankService.updateAssignmentBank(assignmentBank));
    }

    /**
     * 删除作业题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:assignmentBank:remove')")
    @Log(title = "作业题库关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assignmentBankService.deleteAssignmentBankByIds(ids));
    }
}
