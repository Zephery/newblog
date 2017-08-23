package com.myblog.controller;

import com.myblog.model.Blog;
import com.myblog.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zephery on 2017/6/21.
 */
@Controller
public class CategoryController {
    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    private IBlogService blogService;

    @RequestMapping("getbytagid")
    public ModelAndView getbytagid(HttpServletRequest request) {
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        ModelAndView modelAndView = new ModelAndView();
        List<Blog> blogs = blogService.getBlogByTagId(tid);
        modelAndView.addObject("blogs", blogs);
        modelAndView.setViewName("tech");
        return modelAndView;
    }

    @RequestMapping("getbycategoryid")
    public ModelAndView getbycategoryid(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        if (cid == 0) {
            try {
                response.sendRedirect("life.html");
            } catch (IOException e) {
                logger.error("getbycategoryid", e);
            }
        }
        List<Blog> blogs = blogService.getByCategoryId(cid);
        modelAndView.addObject("blog", blogs);
        modelAndView.setViewName("tech");
        return modelAndView;
    }
}
