package com.myblog.service.impl;

import com.myblog.dao.ImageMapper;
import com.myblog.model.Image;
import com.myblog.service.IImageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zephery on 2017/6/18.
 */
@Service("iImageService")
public class ImageServiceImpl implements IImageService {
    @Resource
    private ImageMapper imageMapper;

    @Override
    public List<Image> getAllImage() {
        return imageMapper.getAllImage();
    }

    @Override
    public Integer updateiht(Image image) {
        return imageMapper.updateiht(image);
    }

    @Override
    public Image getImagebyId(Integer imageid) {
        return imageMapper.selectByPrimaryKey(imageid);
    }
}
