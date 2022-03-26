package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Relation;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationMapper extends BaseMapper<Relation> {
    int deleteByPrimaryKey(Integer id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
}