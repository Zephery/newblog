package com.myblog.service.impl;

import com.myblog.dao.BlogMapper;
import com.myblog.dao.CategoryMapper;
import com.myblog.dao.RelationMapper;
import com.myblog.dao.TagMapper;
import com.myblog.lucene.BlogIndex;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Tag;
import com.myblog.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zephery on 2017/1/18.
 */
@Service("blogService")
public class BlogServiceImpl implements IBlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TagMapper tagMapper;
    private BlogIndex blogIndex = new BlogIndex();

    @Override
    public List<Blog> getAllBlog() {
        List<Blog> blogs = blogMapper.getAllBlog();
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getByCategoryId(int categoryid) {
        List<Blog> blogs = blogMapper.getByCategoryId(categoryid);
        for (Blog blog : blogs) {
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public Blog getBlogDetail(Integer blogid) {
        Blog blog = blogMapper.selectByPrimaryKey(blogid);
        if (blog == null) {
            return null;
        }
        Category category = categoryMapper.selectByPrimaryKey(blog.getCategoryid());
        blog.setCategory(category);
        List<Tag> tags = tagMapper.getTagByBlogId(blog.getBlogid());
        blog.setTags(tags.size() > 0 ? tags : null);
        if (blogMapper.updatehits(blogid)) {
            logger.info("read count success");
        } else {
            logger.info("read count failure");
        }
        return blog;
    }

    @Override
    public Tag getTagByTid(Integer t_id) {
        return tagMapper.selectByPrimaryKey(t_id);
    }

    @Override
    public List<Blog> getBlogByTagId(Integer tId) {
        List<Blog> blogs = blogMapper.getBlogByTagId(tId);
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getBanner() {
        List<Blog> blogs = blogMapper.getBanner();
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getByHits() {
        List<Blog> blogs = blogMapper.getHits();
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getLife() {
        List<Blog> blogs = blogMapper.getLife();
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getAllTechBlog() {
        List<Blog> blogs = blogMapper.getAllTechBlog();
        for (Blog blog : blogs) {
            blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
            try {
                blog.setCreateAt(df.format(df.parse(blog.getCreateAt())));
            } catch (Exception e) {
                logger.error("转换格式错误", e);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> getLuceneBlog(Integer pageStart,String keyword,Integer pagehits) {
        List<Blog> blogs = new ArrayList<>();
        try {
            blogs = blogIndex.searchBlog(pageStart,keyword,pagehits);
            for (Blog blog : blogs) {
                blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
                blog.setTags(tagMapper.getTagByBlogId(blog.getBlogid()));
            }
        } catch (Exception e) {
            logger.error("搜索錯誤", e);
        }
        return blogs;
    }
}
