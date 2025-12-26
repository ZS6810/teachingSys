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
import com.teach.teachingsys.domain.ExamBank;
import com.teach.teachingsys.service.IExamBankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试题库关系Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/examBank")
public class ExamBankController extends BaseController
{
    @Autowired
    private IExamBankService examBankService;

    /**
     * 查询考试题库关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamBank examBank)
    {
        startPage();
        List<ExamBank> list = examBankService.selectExamBankList(examBank);
        return getDataTable(list);
    }

    /**
     * 导出考试题库关系列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:export')")
    @Log(title = "考试题库关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamBank examBank)
    {
        List<ExamBank> list = examBankService.selectExamBankList(examBank);
        ExcelUtil<ExamBank> util = new ExcelUtil<ExamBank>(ExamBank.class);
        util.exportExcel(response, list, "考试题库关系数据");
    }

    /**
     * 获取考试题库关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(examBankService.selectExamBankById(id));
    }

    /**
     * 新增考试题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:add')")
    @Log(title = "考试题库关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamBank examBank)
    {
        return toAjax(examBankService.insertExamBank(examBank));
    }

    /**
     * 修改考试题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:edit')")
    @Log(title = "考试题库关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamBank examBank)
    {
        return toAjax(examBankService.updateExamBank(examBank));
    }

    /**
     * 删除考试题库关系
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:examBank:remove')")
    @Log(title = "考试题库关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examBankService.deleteExamBankByIds(ids));
    }
}
