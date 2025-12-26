package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Chapter;

/**
 * 章节Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IChapterService 
{
    /**
     * 查询章节
     * 
     * @param id 章节主键
     * @return 章节
     */
    public Chapter selectChapterById(Long id);

    /**
     * 查询章节列表
     * 
     * @param chapter 章节
     * @return 章节集合
     */
    public List<Chapter> selectChapterList(Chapter chapter);

    /**
     * 新增章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    public int insertChapter(Chapter chapter);

    /**
     * 修改章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    public int updateChapter(Chapter chapter);

    /**
     * 批量删除章节
     * 
     * @param ids 需要删除的章节主键集合
     * @return 结果
     */
    public int deleteChapterByIds(Long[] ids);

    /**
     * 删除章节信息
     * 
     * @param id 章节主键
     * @return 结果
     */
    public int deleteChapterById(Long id);
}
