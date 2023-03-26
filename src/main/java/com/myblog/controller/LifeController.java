package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Tag;
import com.myblog.service.IBlogService;
import com.myblog.service.ICategoryService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Zephery on 2017/6/24.
 */
@Slf4j
@Controller
public class LifeController {
    @Resource
    private IBlogService blogService;
    @Resource
    private ICategoryService categoryService;

    @RequestMapping("/life")
    public ModelAndView toshowarticle(HttpServletRequest request) {       //生活笔记
        String page = request.getParameter("pagenum");
        String categoryid = request.getParameter("categoryid");
        String t_id = request.getParameter("tid");
        Integer pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        PageHelper.startPage(pagenum, 15);
        List<Blog> lists;
        if (StringUtils.isEmpty(categoryid)) {
            lists = blogService.getLife();
        } else if (!StringUtils.isEmpty(t_id)) {
            lists = blogService.getBlogByTagId(Integer.parseInt(t_id));
        } else {
            lists = blogService.getByCategoryId(Integer.parseInt(categoryid));
        }
        ModelAndView modelAndView = new ModelAndView();
        for (Blog list : lists) {
            try {
                int category_id = lists.get(0).getCategoryid();
                list.setCategory(categoryService.selectByPrimaryKey(category_id));
            } catch (Exception e) {
                log.error("datetimeparse error" + e);
            }
        }
        if (categoryid != null && !categoryid.equals("")) {
            Category category = categoryService.selectByPrimaryKey(Integer.parseInt(categoryid));
            modelAndView.addObject("category", category);
        } else {
            if (t_id != null) {
                Tag tag = blogService.getTagByTid(Integer.parseInt(t_id));
                modelAndView.addObject("tag", tag);
            }
        }
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
        modelAndView.setViewName("life");
        return modelAndView;
    }
}
