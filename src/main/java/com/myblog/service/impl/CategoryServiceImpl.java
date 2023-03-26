package com.myblog.service.impl;

import com.myblog.dao.CategoryMapper;
import com.myblog.model.Category;
import com.myblog.service.ICategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zephery on 2017/6/19.
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public Category selectByPrimaryKey(Integer categoryid) {   //TODO 做一下缓存处理
        return categoryMapper.selectByPrimaryKey(categoryid);
    }

    @Override
    public List<Category> getAllCatWithoutLife() {
        return categoryMapper.getAllCatWithoutLife();
    }
}
