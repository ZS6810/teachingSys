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
import com.teach.teachingsys.domain.Learningprogress;
import com.teach.teachingsys.service.ILearningprogressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学习进度Controller
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@RestController
@RequestMapping("/teachingsys/learningprogress")
public class LearningprogressController extends BaseController
{
    @Autowired
    private ILearningprogressService learningprogressService;

    /**
     * 查询学习进度列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:list')")
    @GetMapping("/list")
    public TableDataInfo list(Learningprogress learningprogress)
    {
        startPage();
        List<Learningprogress> list = learningprogressService.selectLearningprogressList(learningprogress);
        return getDataTable(list);
    }

    /**
     * 导出学习进度列表
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:export')")
    @Log(title = "学习进度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Learningprogress learningprogress)
    {
        List<Learningprogress> list = learningprogressService.selectLearningprogressList(learningprogress);
        ExcelUtil<Learningprogress> util = new ExcelUtil<Learningprogress>(Learningprogress.class);
        util.exportExcel(response, list, "学习进度数据");
    }

    /**
     * 获取学习进度详细信息
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(learningprogressService.selectLearningprogressById(id));
    }

    /**
     * 新增学习进度
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:add')")
    @Log(title = "学习进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Learningprogress learningprogress)
    {
        return toAjax(learningprogressService.insertLearningprogress(learningprogress));
    }

    /**
     * 修改学习进度
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:edit')")
    @Log(title = "学习进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Learningprogress learningprogress)
    {
        return toAjax(learningprogressService.updateLearningprogress(learningprogress));
    }

    /**
     * 删除学习进度
     */
    @PreAuthorize("@ss.hasPermi('teachingsys:learningprogress:remove')")
    @Log(title = "学习进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(learningprogressService.deleteLearningprogressByIds(ids));
    }
}
