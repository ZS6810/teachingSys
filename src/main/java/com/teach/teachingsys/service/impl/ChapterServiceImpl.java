package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.ChapterMapper;
import com.teach.teachingsys.domain.Chapter;
import com.teach.teachingsys.service.IChapterService;

/**
 * 章节Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class ChapterServiceImpl implements IChapterService 
{
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询章节
     * 
     * @param id 章节主键
     * @return 章节
     */
    @Override
    public Chapter selectChapterById(Long id)
    {
        return chapterMapper.selectChapterById(id);
    }

    /**
     * 查询章节列表
     * 
     * @param chapter 章节
     * @return 章节
     */
    @Override
    public List<Chapter> selectChapterList(Chapter chapter)
    {
        return chapterMapper.selectChapterList(chapter);
    }

    /**
     * 新增章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    @Override
    public int insertChapter(Chapter chapter)
    {
        return chapterMapper.insertChapter(chapter);
    }

    /**
     * 修改章节
     * 
     * @param chapter 章节
     * @return 结果
     */
    @Override
    public int updateChapter(Chapter chapter)
    {
        return chapterMapper.updateChapter(chapter);
    }

    /**
     * 批量删除章节
     * 
     * @param ids 需要删除的章节主键
     * @return 结果
     */
    @Override
    public int deleteChapterByIds(Long[] ids)
    {
        return chapterMapper.deleteChapterByIds(ids);
    }

    /**
     * 删除章节信息
     * 
     * @param id 章节主键
     * @return 结果
     */
    @Override
    public int deleteChapterById(Long id)
    {
        return chapterMapper.deleteChapterById(id);
    }
}
