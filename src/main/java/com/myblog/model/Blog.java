package com.myblog.model;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {
    /**
     * 博客id，主键
     */
    private Integer blogid;
    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客摘要，从博客文本中选取前200个字符
     */
    private String summary;
    /**
     * 博客文本
     */
    private String content;
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 类别id
     */
    private Integer categoryid;
    /**
     * 浏览量
     */
    private Integer hits;
    /**
     * 图片的url，选取第一张jpg/png图片作为文章的图片
     */
    private String imageurl;
    /**
     * 类别
     */
    private Category category;
    /**
     * 标签
     */
    private List<Tag> tags;

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}