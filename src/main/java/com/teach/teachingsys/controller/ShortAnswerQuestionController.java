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
import com.teach.teachingsys.domain.ShortAnswerQuestion;
import com.teach.teachingsys.service.IShortAnswerQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 简答题Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/answerQuestion")
public class ShortAnswerQuestionController extends BaseController
{
    @Autowired
    private IShortAnswerQuestionService shortAnswerQuestionService;

    /**
     * 查询简答题列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShortAnswerQuestion shortAnswerQuestion)
    {
        startPage();
        List<ShortAnswerQuestion> list = shortAnswerQuestionService.selectShortAnswerQuestionList(shortAnswerQuestion);
        return getDataTable(list);
    }

    /**
     * 导出简答题列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:export')")
    @Log(title = "简答题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShortAnswerQuestion shortAnswerQuestion)
    {
        List<ShortAnswerQuestion> list = shortAnswerQuestionService.selectShortAnswerQuestionList(shortAnswerQuestion);
        ExcelUtil<ShortAnswerQuestion> util = new ExcelUtil<ShortAnswerQuestion>(ShortAnswerQuestion.class);
        util.exportExcel(response, list, "简答题数据");
    }

    /**
     * 获取简答题详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shortAnswerQuestionService.selectShortAnswerQuestionById(id));
    }

    /**
     * 新增简答题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:add')")
    @Log(title = "简答题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShortAnswerQuestion shortAnswerQuestion)
    {
        return toAjax(shortAnswerQuestionService.insertShortAnswerQuestion(shortAnswerQuestion));
    }

    /**
     * 修改简答题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:edit')")
    @Log(title = "简答题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShortAnswerQuestion shortAnswerQuestion)
    {
        return toAjax(shortAnswerQuestionService.updateShortAnswerQuestion(shortAnswerQuestion));
    }

    /**
     * 删除简答题
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:answerQuestion:remove')")
    @Log(title = "简答题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shortAnswerQuestionService.deleteShortAnswerQuestionByIds(ids));
    }
}
