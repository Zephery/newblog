package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    int deleteByPrimaryKey(Integer cId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getAllCategory();

    List<Category> getAllCatWithoutLife();
}