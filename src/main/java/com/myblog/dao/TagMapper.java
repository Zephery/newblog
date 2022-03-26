package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Blog;
import com.myblog.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {
    int deleteByPrimaryKey(Integer tId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> getTagByBlogId(Integer blogid);

    List<Tag> getAllTags();

    List<Blog> getblogbytagid(Integer tId);

    Tag selectByName(String t_name);
}