package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Post;

/**
 * 帖子Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IPostService 
{
    /**
     * 查询帖子
     * 
     * @param id 帖子主键
     * @return 帖子
     */
    public Post selectPostById(Long id);

    /**
     * 查询帖子列表
     * 
     * @param post 帖子
     * @return 帖子集合
     */
    public List<Post> selectPostList(Post post);

    /**
     * 新增帖子
     * 
     * @param post 帖子
     * @return 结果
     */
    public int insertPost(Post post);

    /**
     * 修改帖子
     * 
     * @param post 帖子
     * @return 结果
     */
    public int updatePost(Post post);

    /**
     * 批量删除帖子
     * 
     * @param ids 需要删除的帖子主键集合
     * @return 结果
     */
    public int deletePostByIds(Long[] ids);

    /**
     * 删除帖子信息
     * 
     * @param id 帖子主键
     * @return 结果
     */
    public int deletePostById(Long id);
}
