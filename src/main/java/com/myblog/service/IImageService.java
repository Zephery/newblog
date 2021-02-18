package com.myblog.service;

import com.myblog.model.Image;

import java.util.List;

/**
 * Created by Zephery on 2017/6/18.
 */
public interface IImageService {
    List<Image> getAllImage();

    Integer updateiht(Image image);

    Image getImagebyId(Integer imageid);
}
