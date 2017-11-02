package com.myblog.service;

import com.myblog.model.Category;

import java.util.List;

/**
 * Created by Zephery on 2017/6/19.
 */
public interface ICategoryService {
    public List<Category> getAllCategory();

    public Category selectByPrimaryKey(Integer categoryid);

    List<Category> getAllCatWithoutLife();
}
