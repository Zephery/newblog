package com.myblog.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myblog.dao.TagMapper;
import com.myblog.model.Blog;
import com.myblog.model.KeyAndValue;
import com.myblog.model.Tag;
import com.myblog.service.ITagService;
import com.myblog.util.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
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
    private RedisTemplate<String, String> redisTemplate;

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
        Iterator iterator = jsonArray.iterator();
        List<KeyAndValue> biaoqian = new ArrayList<>();
        while (iterator.hasNext()) {
            Gson gson = new Gson();
            KeyAndValue keyAndValue = gson.fromJson((JsonObject) iterator.next(), KeyAndValue.class);
            biaoqian.add(keyAndValue);
        }
        biaoqian.sort((o1, o2) -> {
            Integer a = StringUtil.stringgetint(o1.getValue());
            Integer b = StringUtil.stringgetint(o2.getValue());
            return b.compareTo(a);
        });
        Gson gson = new Gson();
        String temp = gson.toJson(biaoqian.size() > 16 ? biaoqian.subList(0, 16) : biaoqian);
        redisTemplate.opsForValue().set("biaoqian", temp);
        return tId;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public void deleteTag(Integer tId) throws RuntimeException {
        tagMapper.deleteByPrimaryKey(tId);
    }

    @Override
    public String getBiaoqian() throws Exception {
        if (redisTemplate.opsForValue().get("biaoqian") == null) {
            updatetag(1);
        }
        return redisTemplate.opsForValue().get("biaoqian");
    }


}
