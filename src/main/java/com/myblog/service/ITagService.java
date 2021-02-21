package com.myblog.service;

import com.myblog.model.Tag;

import java.util.List;

/**
 * Created by Zephery on 2017/6/20.
 */
public interface ITagService {
    Integer updatetag(Integer tId);

    List<Tag> getAllTags();

    void deleteTag(Integer tId) throws RuntimeException;

    String getBiaoqian() throws Exception;
}
