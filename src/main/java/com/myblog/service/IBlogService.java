package com.myblog.service;

import com.myblog.model.Blog;
import com.myblog.model.Tag;

import java.util.List;

/**
 * Created by Zephery on 2017/6/18.
 */
public interface IBlogService {
    public List<Blog> getAllBlog();
    public List<Blog> getByCategoryId(int categoryid);
    public Blog getBlogDetail(Integer blogid);
    public Tag getTagByTid(Integer t_id);
    public List<Blog> getBlogByTagId(Integer tId);
    public List<Blog> getBanner();
    public List<Blog> getByHits();
    public List<Blog> getLife();
    public List<Blog> getAllTechBlog();
}
