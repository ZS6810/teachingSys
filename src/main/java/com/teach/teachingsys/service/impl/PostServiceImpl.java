package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.PostMapper;
import com.teach.teachingsys.domain.Post;
import com.teach.teachingsys.service.IPostService;

/**
 * 帖子Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class PostServiceImpl implements IPostService 
{
    @Autowired
    private PostMapper postMapper;

    /**
     * 查询帖子
     * 
     * @param id 帖子主键
     * @return 帖子
     */
    @Override
    public Post selectPostById(Long id)
    {
        return postMapper.selectPostById(id);
    }

    /**
     * 查询帖子列表
     * 
     * @param post 帖子
     * @return 帖子
     */
    @Override
    public List<Post> selectPostList(Post post)
    {
        return postMapper.selectPostList(post);
    }

    /**
     * 新增帖子
     * 
     * @param post 帖子
     * @return 结果
     */
    @Override
    public int insertPost(Post post)
    {
        return postMapper.insertPost(post);
    }

    /**
     * 修改帖子
     * 
     * @param post 帖子
     * @return 结果
     */
    @Override
    public int updatePost(Post post)
    {
        return postMapper.updatePost(post);
    }

    /**
     * 批量删除帖子
     * 
     * @param ids 需要删除的帖子主键
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] ids)
    {
        return postMapper.deletePostByIds(ids);
    }

    /**
     * 删除帖子信息
     * 
     * @param id 帖子主键
     * @return 结果
     */
    @Override
    public int deletePostById(Long id)
    {
        return postMapper.deletePostById(id);
    }
}
