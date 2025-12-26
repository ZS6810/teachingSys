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
import com.teach.teachingsys.domain.Questionbank;
import com.teach.teachingsys.service.IQuestionbankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题库Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/questionbank")
public class QuestionbankController extends BaseController
{
    @Autowired
    private IQuestionbankService questionbankService;

    /**
     * 查询题库列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:list')")
    @GetMapping("/list")
    public TableDataInfo list(Questionbank questionbank)
    {
        startPage();
        List<Questionbank> list = questionbankService.selectQuestionbankList(questionbank);
        return getDataTable(list);
    }

    /**
     * 导出题库列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:export')")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Questionbank questionbank)
    {
        List<Questionbank> list = questionbankService.selectQuestionbankList(questionbank);
        ExcelUtil<Questionbank> util = new ExcelUtil<Questionbank>(Questionbank.class);
        util.exportExcel(response, list, "题库数据");
    }

    /**
     * 获取题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(questionbankService.selectQuestionbankById(id));
    }

    /**
     * 新增题库
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:add')")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Questionbank questionbank)
    {
        return toAjax(questionbankService.insertQuestionbank(questionbank));
    }

    /**
     * 修改题库
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:edit')")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Questionbank questionbank)
    {
        return toAjax(questionbankService.updateQuestionbank(questionbank));
    }

    /**
     * 删除题库
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:questionbank:remove')")
    @Log(title = "题库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(questionbankService.deleteQuestionbankByIds(ids));
    }
}
