package com.myblog.service;

import com.myblog.model.Image;

import java.util.List;

/**
 * Created by Zephery on 2017/6/18.
 */
public interface IImageService {
    public List<Image> getAllImage();

    public Integer updateiht(Image image);

    public Image getImagebyId(Integer imageid);
}
