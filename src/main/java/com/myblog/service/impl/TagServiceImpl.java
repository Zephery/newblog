package com.myblog.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myblog.dao.CategoryMapper;
import com.myblog.dao.TagMapper;
import com.myblog.model.Blog;
import com.myblog.model.KeyAndValue;
import com.myblog.model.Tag;
import com.myblog.service.ITagService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zephery on 2017/6/20.
 */
@Service("tagService")
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements ITagService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Integer updatetag(Integer tId) {
        //标签
        List<Tag> tags = tagMapper.getAllTags();
        JsonArray jsonArray = new JsonArray();
        for (Tag tag : tags) {
            List<Blog> blogs = tagMapper.getblogbytagid(tag.gettId());
            String str = tag.gettName() + " " + "(" + String.valueOf(blogs.size()) + ")";
            KeyAndValue keyAndValue = new KeyAndValue();
            keyAndValue.setKey(tag.gettName());
            keyAndValue.setValue(str);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key", tag.gettId());
            jsonObject.addProperty("value", str);
            jsonArray.add(jsonObject);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize("biaoqian"), serializer.serialize(jsonArray.toString()));
                return true;
            }
        });
        return tId;
    }

    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public void deleteTag(Integer tId) throws RuntimeException {
        tagMapper.deleteByPrimaryKey(tId);
    }
}
