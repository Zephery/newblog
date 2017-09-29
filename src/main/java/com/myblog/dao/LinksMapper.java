package com.myblog.dao;

import com.myblog.model.Links;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinksMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(Links record);

    int insertSelective(Links record);

    Links selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(Links record);

    int updateByPrimaryKey(Links record);

    List<Links> getAllLinks();
}