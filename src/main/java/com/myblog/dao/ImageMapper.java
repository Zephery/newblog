package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMapper extends BaseMapper<Image> {
    int deleteByPrimaryKey(Integer imageid);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imageid);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> getAllImage();

    int updateiht(Image image);
}