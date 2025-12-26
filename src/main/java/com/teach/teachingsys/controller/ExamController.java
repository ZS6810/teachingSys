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
import com.teach.teachingsys.domain.Exam;
import com.teach.teachingsys.service.IExamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/exam")
public class ExamController extends BaseController
{
    @Autowired
    private IExamService examService;

    /**
     * 查询考试列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:list')")
    @GetMapping("/list")
    public TableDataInfo list(Exam exam)
    {
        startPage();
        List<Exam> list = examService.selectExamList(exam);
        return getDataTable(list);
    }

    /**
     * 导出考试列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:export')")
    @Log(title = "考试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Exam exam)
    {
        List<Exam> list = examService.selectExamList(exam);
        ExcelUtil<Exam> util = new ExcelUtil<Exam>(Exam.class);
        util.exportExcel(response, list, "考试数据");
    }

    /**
     * 获取考试详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(examService.selectExamById(id));
    }

    /**
     * 新增考试
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:add')")
    @Log(title = "考试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Exam exam)
    {
        return toAjax(examService.insertExam(exam));
    }

    /**
     * 修改考试
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:edit')")
    @Log(title = "考试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Exam exam)
    {
        return toAjax(examService.updateExam(exam));
    }

    /**
     * 删除考试
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:exam:remove')")
    @Log(title = "考试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examService.deleteExamByIds(ids));
    }
}
