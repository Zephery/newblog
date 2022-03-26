package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    int deleteByPrimaryKey(Integer blogid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer blogid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 原本是根据order by create_at，但是速度实在太慢，更改为blogid
     *
     * @return
     */
    List<Blog> getAllBlog();

    List<Blog> getByCategoryId(@Param("categoryid") Integer categoryid);

    List<Blog> getBlogByTagId(Integer tId);

    List<Blog> getBanner();

    List<Blog> getHits();

    boolean updatehits(Integer blogid);

    List<Blog> getLife();

    List<Blog> getAllTechBlog();

    List<Blog> getAllBlogWithContent();

    public Blog preBlog(Integer blogId);

    public Blog nextBlog(Integer blogId);
}