package com.myblog.lucene;

import com.myblog.model.Blog;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.lucene.search.suggest.InputIterator;
import org.apache.lucene.util.BytesRef;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/7/26 11:50
 * Description:
 */
public class BlogIterator implements InputIterator {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BlogIterator.class);
    private Iterator<Blog> blogIterator;
    private Blog currentBlog;

    public BlogIterator(Iterator<Blog> blogIterator) {
        this.blogIterator = blogIterator;
    }

    @Override
    public boolean hasContexts() {
        return true;
    }

    @Override
    public boolean hasPayloads() {
        return true;
    }

    public Comparator<BytesRef> getComparator() {
        return null;
    }

    @Override
    public BytesRef next() {
        if (blogIterator.hasNext()) {
            currentBlog = blogIterator.next();
            try {
                //返回当前Project的name值，把blog类的name属性值作为key
                return new BytesRef(Jsoup.parse(currentBlog.getTitle()).text().getBytes("utf8"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 将Blog对象序列化存入payload
     * 可以只将所需要的字段存入payload，这里对整个实体类进行序列化，方便以后需求，不建议采用这种方法
     */
    @Override
    public BytesRef payload() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(currentBlog);
            out.close();
            BytesRef bytesRef = new BytesRef(bos.toByteArray());
            return bytesRef;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 文章标题
     */
    @Override
    public Set<BytesRef> contexts() {
        try {
            Set<BytesRef> regions = new HashSet<BytesRef>();
            regions.add(new BytesRef(currentBlog.getTitle().getBytes("UTF8")));
            return regions;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Couldn't convert to UTF-8");
        }
    }

    /**
     * 返回权重值，这个值会影响排序
     * 这里以产品的销售量作为权重值，weight值即最终返回的热词列表里每个热词的权重值
     */
    @Override
    public long weight() {
        return currentBlog.getHits();   //change to hits
    }
}