package com.myblog.service;

import com.myblog.model.Blog;
import com.myblog.model.Tag;

import java.util.List;
import java.util.Set;

/**
 * Created by Zephery on 2017/6/18.
 */
public interface IBlogService {
    List<Blog> getAllBlog();

    List<Blog> getByCategoryId(int categoryid);

    Blog getBlogDetail(Integer blogid);

    Tag getTagByTid(Integer t_id);

    List<Blog> getBlogByTagId(Integer tId);

    List<Blog> getBanner();

    List<Blog> getByHits();

    List<Blog> getLife();

    List<Blog> getAllTechBlog();

    List<Blog> getLuceneBlog(Integer pageStart, String keyword, Integer pagehits);

    void ajaxbuild();

    Set<String> ajaxsearch(String keyword);

    List<Blog> getAllBlogWithContent();

    Blog preBlog(Integer blogId);

    Blog nextBlog(Integer blogId);
}
