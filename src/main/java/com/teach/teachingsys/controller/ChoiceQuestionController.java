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
import com.teach.teachingsys.domain.ChoiceQuestion;
import com.teach.teachingsys.service.IChoiceQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 选择题Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/choiceQuestion")
public class ChoiceQuestionController extends BaseController
{
    @Autowired
    private IChoiceQuestionService choiceQuestionService;

    /**
     * 查询选择题列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChoiceQuestion choiceQuestion)
    {
        startPage();
        List<ChoiceQuestion> list = choiceQuestionService.selectChoiceQuestionList(choiceQuestion);
        return getDataTable(list);
    }

    /**
     * 导出选择题列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:export')")
    @Log(title = "选择题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChoiceQuestion choiceQuestion)
    {
        List<ChoiceQuestion> list = choiceQuestionService.selectChoiceQuestionList(choiceQuestion);
        ExcelUtil<ChoiceQuestion> util = new ExcelUtil<ChoiceQuestion>(ChoiceQuestion.class);
        util.exportExcel(response, list, "选择题数据");
    }

    /**
     * 获取选择题详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(choiceQuestionService.selectChoiceQuestionById(id));
    }

    /**
     * 新增选择题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:add')")
    @Log(title = "选择题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChoiceQuestion choiceQuestion)
    {
        return toAjax(choiceQuestionService.insertChoiceQuestion(choiceQuestion));
    }

    /**
     * 修改选择题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:edit')")
    @Log(title = "选择题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChoiceQuestion choiceQuestion)
    {
        return toAjax(choiceQuestionService.updateChoiceQuestion(choiceQuestion));
    }

    /**
     * 删除选择题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:choiceQuestion:remove')")
    @Log(title = "选择题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(choiceQuestionService.deleteChoiceQuestionByIds(ids));
    }
}
