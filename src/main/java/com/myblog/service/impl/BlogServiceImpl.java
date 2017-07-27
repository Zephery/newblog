package com.myblog.service.impl;

import com.myblog.dao.BlogMapper;
import com.myblog.dao.CategoryMapper;
import com.myblog.dao.TagMapper;
import com.myblog.lucene.BlogIndex;
import com.myblog.lucene.BlogIterator;
import com.myblog.lucene.Tools;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Tag;
import com.myblog.service.IBlogService;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.suggest.Lookup.LookupResult;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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
    public List<Blog> getLuceneBlog(Integer pageStart, String keyword, Integer pagehits) {
        List<Blog> blogs = new ArrayList<>();
        try {
            blogs = blogIndex.searchBlog(pageStart, keyword, pagehits);
            for (Blog blog : blogs) {
                blog.setCategory(categoryMapper.selectByPrimaryKey(blog.getCategoryid()));
                blog.setTags(tagMapper.getTagByBlogId(blog.getBlogid()));
            }
        } catch (Exception e) {
            logger.error("搜索錯誤", e);
        }
        return blogs;
    }

    @Override
    public void ajaxbuild() {
        try {
            Directory dir = FSDirectory.open(Paths.get("autocomplete"));
            RAMDirectory indexDir = new RAMDirectory();   //   RAM
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            AnalyzingInfixSuggester suggester = new AnalyzingInfixSuggester(dir, analyzer);
            //创建Blog测试数据
            List<Blog> blogs = blogMapper.getAllBlog();
            suggester.build(new BlogIterator(blogs.iterator()));
        } catch (IOException e) {
            System.err.println("Error!");
        }
    }

    @Override
    public List<String> ajaxsearch(String keyword) {
        try {
            Directory dir = FSDirectory.open(Paths.get("autocomplete"));
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            AnalyzingInfixSuggester suggester = new AnalyzingInfixSuggester(dir, analyzer);
            List<String> list = lookup(suggester, keyword);
            return list;
        } catch (IOException e) {
            System.err.println("Error!");
            return null;
        }
    }

    /**
     * lookup
     *
     * @param suggester
     * @param keyword
     * @throws IOException
     */
    private static List<String> lookup(AnalyzingInfixSuggester suggester, String keyword
    ) throws IOException {
        //先以contexts为过滤条件进行过滤，再以title为关键字进行筛选，根据weight值排序返回前2条
        //第3个布尔值即是否每个Term都要匹配，第4个参数表示是否需要关键字高亮
        List<LookupResult> results = suggester.lookup(keyword, 10, true, true);
        System.out.println(keyword);
        List<String> list = new ArrayList<>();
        for (LookupResult result : results) {
            list.add(result.key.toString());
            //从payload中反序列化出Blog对象
            BytesRef bytesRef = result.payload;
            InputStream is = Tools.bytes2InputStream(bytesRef.bytes);
            Blog blog = (Blog) Tools.deSerialize(is);
//            System.out.println("blog-Name:" + blog.getTitle());
//            System.out.println("blog-Content:" + blog.getContent());
//            System.out.println("blog-image:" + blog.getImageurl());
//            System.out.println("blog-numberSold:" + blog.getHits());
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            Directory dir = FSDirectory.open(Paths.get("autocomplete"));
            RAMDirectory indexDir = new RAMDirectory();
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            AnalyzingInfixSuggester suggester = new AnalyzingInfixSuggester(dir, analyzer);
            IBlogService blogService = new BlogServiceImpl();
            lookup(suggester, "jav");
//            new BlogServiceImpl().ajaxsearch("北京");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
