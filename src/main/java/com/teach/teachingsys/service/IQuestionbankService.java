package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Questionbank;

/**
 * 题库Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IQuestionbankService 
{
    /**
     * 查询题库
     * 
     * @param id 题库主键
     * @return 题库
     */
    public Questionbank selectQuestionbankById(Long id);

    /**
     * 查询题库列表
     * 
     * @param questionbank 题库
     * @return 题库集合
     */
    public List<Questionbank> selectQuestionbankList(Questionbank questionbank);

    /**
     * 新增题库
     * 
     * @param questionbank 题库
     * @return 结果
     */
    public int insertQuestionbank(Questionbank questionbank);

    /**
     * 修改题库
     * 
     * @param questionbank 题库
     * @return 结果
     */
    public int updateQuestionbank(Questionbank questionbank);

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的题库主键集合
     * @return 结果
     */
    public int deleteQuestionbankByIds(Long[] ids);

    /**
     * 删除题库信息
     * 
     * @param id 题库主键
     * @return 结果
     */
    public int deleteQuestionbankById(Long id);
}
