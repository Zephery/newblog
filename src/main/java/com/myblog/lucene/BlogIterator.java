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
    private Iterator<Blog> productIterator;
    private Blog currentBlog;

    public BlogIterator(Iterator<Blog> productIterator) {
        this.productIterator = productIterator;
    }

    public boolean hasContexts() {
        return true;
    }


    public boolean hasPayloads() {
        return true;
    }

    public Comparator<BytesRef> getComparator() {
        return null;
    }

    public BytesRef next() {
        if (productIterator.hasNext()) {
            currentBlog = productIterator.next();
            try {
                //返回当前Project的name值，把product类的name属性值作为key
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
     * [这里仅仅是个示例，其实这种做法不可取,一般不会把整个对象存入payload,这样索引体积会很大，浪费硬盘空间]
     */
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
     * 把产品的销售区域存入context，context里可以是任意的自定义数据，一般用于数据过滤
     * Set集合里的每一个元素都会被创建一个TermQuery，你只是提供一个Set集合，至于new TermQuery
     * Lucene底层API去做了，但你必须要了解底层干了些什么
     */
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
     * 怎么设计返回这个权重值，发挥你们的想象力吧
     */
    public long weight() {
        return currentBlog.getHits();   //change to hits
    }
}