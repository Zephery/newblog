package com.myblog.service;

import com.myblog.model.Tag;

import java.util.List;

/**
 * Created by Zephery on 2017/6/20.
 */
public interface ITagService {
    public Integer updatetag(Integer tId);

    public List<Tag> getAllTags();

    public void deleteTag(Integer tId) throws RuntimeException;

    public String getBiaoqian() throws Exception;
}
