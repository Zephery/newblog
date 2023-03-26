package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Tag;
import com.myblog.service.IAsyncService;
import com.myblog.service.IBlogService;
import com.myblog.service.ICategoryService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Zephery on 2016/8/5.
 */
@Slf4j
@Controller
public class BlogController {
    @Resource
    private IBlogService blogService;
    @Resource
    private ICategoryService categoryService;
    @Resource
    private IAsyncService asyncService;


    @RequestMapping("/tech")
    public ModelAndView to_show_article(HttpServletRequest request) {       //博客主页
        ModelAndView modelAndView = new ModelAndView();
        String page = request.getParameter("pagenum");
        String categoryid = request.getParameter("categoryid");
        String t_id = request.getParameter("tid");
        int pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        PageHelper.startPage(pagenum, 15);
        List<Blog> lists;
        if (StringUtils.isNotEmpty(categoryid)) {
            lists = blogService.getByCategoryId(Integer.parseInt(categoryid));
            Category category = categoryService.selectByPrimaryKey(Integer.parseInt(categoryid));
            modelAndView.addObject("category", category);
        } else if (StringUtils.isNotEmpty(t_id)) {
            lists = blogService.getBlogByTagId(Integer.parseInt(t_id));
            Tag tag = blogService.getTagByTid(Integer.parseInt(t_id));
            modelAndView.addObject("tag", tag);
        } else {
            lists = blogService.getAllTechBlog();
        }
        for (Blog list : lists) {
            try {
                int category_id = list.getCategoryid();//bug fix 2017-11-13
                list.setCategory(categoryService.selectByPrimaryKey(category_id));
            } catch (Exception e) {
                log.error("分类设置" + e);
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
        modelAndView.setViewName("tech");
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getblogdetail")
    public ModelAndView getBlogDetail(Integer blogid) {       //博客具体内容
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogService.getBlogDetail(blogid);
        Blog preblog = blogService.preBlog(blogid);
        if (preblog != null) {
            modelAndView.addObject("preblog", preblog);
        }
        Blog nextblog = blogService.nextBlog(blogid);
        if (nextblog != null) {
            modelAndView.addObject("nextblog", nextblog);
        }
        modelAndView.addObject("blog", blog);
        modelAndView.setViewName("blogdetail");
        asyncService.updatebloghits(blogid);//异步更新阅读次数
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getcategory")
    public ModelAndView getcategory() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pagenum", required = false) Integer pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (pagenum == null) {
                pagenum = 1;
            }
            List<Blog> lists = blogService.getLuceneBlog(pagenum, keyword, 10);
            PageHelper.startPage(pagenum, 10);
            PageInfo<Blog> blogs = new PageInfo<>(lists);
            blogs.setPageSize(10);
            blogs.setSize(10);
            blogs.setPages(lists.size() / 10 == 0 ? lists.size() / 10 : lists.size() / 10 + 1);
            Integer startpage, endpage;
            if (blogs.getPages() < 6) {
                startpage = 1;
                endpage = blogs.getPages();
            } else {
                if (pagenum > 3) {
                    startpage = blogs.getPageNum() - 3;
                    endpage = blogs.getPageNum() + 3;
                } else {
                    startpage = 1;
                    endpage = blogs.getPageNum() + 4;
                }
            }
            modelAndView.addObject("startpage", startpage);
            modelAndView.addObject("endpage", endpage);
            modelAndView.addObject("blogs", blogs.getList());
            modelAndView.addObject("totalpages", blogs.getPages());
            modelAndView.addObject("pageNum", pagenum);
            modelAndView.addObject("keyword", keyword);
            modelAndView.setViewName("searchresult");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("search" + e);
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/board")
    public ModelAndView getboard() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("board");
        } catch (Exception e) {
            log.error("getboard" + e);
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        String md = "# hello world";
        modelAndView.addObject("md", md);
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping("/ajaxbuild")
    public void buildsearch(HttpServletResponse response) throws IOException {
        blogService.ajaxbuild();
        response.getWriter().write("success");

    }

    @RequestMapping("/ajaxsearch")
    public void ajaxsearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        if (StringUtils.isEmpty(keyword)) {
            return;
        }
        Set<String> set = blogService.ajaxsearch(keyword);
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(set));
    }
}
