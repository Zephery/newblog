package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.model.Blog;
import com.myblog.model.Image;
import com.myblog.service.IImageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Zephery on 2017/6/19.
 */
@Controller
public class TripController {
    @Resource
    private IImageService imageService;

    @RequestMapping("trip")
    public ModelAndView gettrip(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String page = request.getParameter("page");
        Integer pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        modelAndView.setViewName("trip");
        PageHelper.startPage(pagenum, 15);
        List<Image> lists = imageService.getAllImage();
        PageInfo<Image> images = new PageInfo<>(lists);
        modelAndView.addObject("pageinfo",images);
        modelAndView.addObject("images", images.getList());
        return modelAndView;
    }
}
