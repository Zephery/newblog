package com.myblog.dao;

import com.myblog.model.Relation;
import com.myblog.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    List<Relation> getBlogByTagId(Integer tag_id);

    Relation gettagidbyblogid(Integer blogid);

    Tag getTagByBlogId(Integer blogid);
}