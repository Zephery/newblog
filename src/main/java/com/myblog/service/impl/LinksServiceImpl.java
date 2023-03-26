package com.myblog.service.impl;

import com.myblog.dao.LinksMapper;
import com.myblog.model.Links;
import com.myblog.service.ILinksService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/28 18:31
 * Description:
 */
@Service("linksService")
public class LinksServiceImpl implements ILinksService {
    @Resource
    private LinksMapper linksMapper;

    @Override
    public List<Links> getAllLinks() {
        return linksMapper.getAllLinks();
    }
}