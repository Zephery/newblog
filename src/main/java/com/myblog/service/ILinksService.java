package com.myblog.service;

import com.myblog.model.Links;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/28 18:31
 * Description:
 */
public interface ILinksService {
    /**
     * 友情链接
     *
     * @return
     */
    List<Links> getAllLinks();
}
