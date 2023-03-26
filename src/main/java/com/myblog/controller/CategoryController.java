package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.model.Blog;
import com.myblog.service.IBlogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/getbytagid")
    public ModelAndView getbytagid(HttpServletRequest request) {
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        ModelAndView modelAndView = new ModelAndView();
        List<Blog> blogs = blogService.getBlogByTagId(tid);
        modelAndView.addObject("blogs", blogs);
        modelAndView.setViewName("tech");
        return modelAndView;
    }

    @RequestMapping("/getbycategoryid")
    public ModelAndView getbycategoryid(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        String page = request.getParameter("pagenum");
        String categoryid = request.getParameter("categoryid");
        String t_id = request.getParameter("tid");
        if (cid == 0) {
            try {
                response.sendRedirect("life.html");
            } catch (IOException e) {
                logger.error("getbycategoryid", e);
            }
        }
        Integer pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        PageHelper.startPage(pagenum, 15);
        List<Blog> lists = blogService.getByCategoryId(cid);
        PageInfo<Blog> blogs = new PageInfo<>(lists);
        Integer startpage, endpage;
        if (blogs.getPages() < 6) {
            startpage = 1;
            endpage = blogs.getPages();
        } else {
            if (pagenum > 3) {
                startpage = blogs.getPageNum() - 3;
                endpage = blogs.getPageNum() + 3 > blogs.getPages() ? blogs.getPages() : pagenum + 3;
            } else {
                startpage = 1;
                endpage = blogs.getPageNum() + 4 > blogs.getPages() ? blogs.getPages() : pagenum + 4;
            }
        }
        modelAndView.addObject("startpage", startpage);
        modelAndView.addObject("endpage", endpage);
        modelAndView.addObject("blogs", blogs.getList());
        modelAndView.addObject("totalpages", blogs.getPages());
        modelAndView.addObject("pageNum", blogs.getPageNum());
        modelAndView.setViewName("tech");
        return modelAndView;
    }
}
