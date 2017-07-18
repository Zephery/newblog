package com.myblog.service.impl;

import com.myblog.dao.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zephery on 2017/6/18.
 */
@Service("iImageService")
public class ImageServiceImpl {
    @Autowired
    private ImageMapper imageMapper;
}
