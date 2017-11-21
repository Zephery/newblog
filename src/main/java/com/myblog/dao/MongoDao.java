package com.myblog.dao;

import com.myblog.model.CriteriaMap;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Zephery
 * @since 2017/11/19 18:51
 */
@Repository
@EnableAsync
public class MongoDao implements IMongoDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Async
    public void insert(Object objectToSave, String tableName) {
        mongoTemplate.insert(objectToSave, tableName);
    }

    public void insertBath(Collection<? extends Object> batchToSave, String tableName) {
        mongoTemplate.insert(batchToSave, tableName);
    }

    public void deleteByMap(CriteriaMap criteriaMap, String tableName) {

        Query query = getQuery(criteriaMap);

        mongoTemplate.remove(query, tableName);
    }

    public void deleteAll(String tableName) {
        mongoTemplate.dropCollection(tableName);
    }

    public void update(CriteriaMap criteriaMap, Map<String, Object> setMap, String tableName) {

        Query query = getQuery(criteriaMap);

        Update update = null;
        for (String key : setMap.keySet()) {
            Object obj = setMap.get(key);// 得到每个key对用value的值
            if (update == null) {
                update = Update.update(key, obj);
            } else {
                update = update.set(key, obj);
            }
        }

        mongoTemplate.updateMulti(query, update, tableName);
    }

    public <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> c, String tableName) {

        Query query = getQuery(criteriaMap);

        return mongoTemplate.find(query, c, tableName);
    }

    public <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> c, int skip, int limit, String tableName) {

        Query query = getQuery(criteriaMap);
        query.skip(skip);
        query.limit(limit);

        return mongoTemplate.find(query, c, tableName);
    }

    public <T> List<T> findByMap4Page(CriteriaMap criteriaMap, Class<T> c, int pageNo, int pageSize, String tableName) {

        Query query = getQuery(criteriaMap);

        query.skip((pageNo - 1) * pageSize).limit(pageSize);

        // query = query.skip((pageNo - 1) * pageSize).limit(pageSize);

        return mongoTemplate.find(query, c, tableName);
    }

    public <T> List<T> findAll(Class<T> c, String tableName) {
        return mongoTemplate.findAll(c, tableName);
    }

    public <T> T findAndModify(CriteriaMap criteriaMap, Map<String, Object> setMap, Class<T> c, String tableName) {
        Query query = getQuery(criteriaMap);
        Update update = getUpdater(setMap);
        return mongoTemplate.findAndModify(query, update, c, tableName);
    }

    public <T> T findAndRemove(CriteriaMap criteriaMap, Class<T> c, String tableName) {
        Query query = getQuery(criteriaMap);
        return mongoTemplate.findAndRemove(query, c, tableName);
    }

    public long count(CriteriaMap criteriaMap, String tableName) {
        Query query = getQuery(criteriaMap);
        return mongoTemplate.count(query, tableName);
    }

    private Query getQuery(CriteriaMap criteriaMap) {

        Criteria criteria = null;
        for (String key : criteriaMap.keySet()) {
            CriteriaMap.Data d = criteriaMap.get(key);
            switch (d.getType()) {
                case is:
                    if (criteria == null) {
                        criteria = Criteria.where(key).is(d.getObj());
                    } else {
                        criteria = criteria.and(key).is(d.getObj());
                    }
                    break;
                case regex:
                    if (criteria == null) {
                        criteria = Criteria.where(key).regex(d.getObj().toString());
                    } else {
                        criteria = criteria.and(key).regex(d.getObj().toString());
                    }
                    break;
                case gt:
                    if (criteria == null) {
                        criteria = Criteria.where(key).gt(d.getObj());
                    } else {
                        criteria = criteria.and(key).gt(d.getObj());
                    }
                    break;
                case gte:
                    if (criteria == null) {
                        criteria = Criteria.where(key).gte(d.getObj());
                    } else {
                        criteria = criteria.and(key).gte(d.getObj());
                    }
                    break;
                case lt:
                    if (criteria == null) {
                        criteria = Criteria.where(key).lt(d.getObj());
                    } else {
                        criteria = criteria.and(key).lt(d.getObj());
                    }
                    break;
                case lte:
                    if (criteria == null) {
                        criteria = Criteria.where(key).lte(d.getObj());
                    } else {
                        criteria = criteria.and(key).lte(d.getObj());
                    }
                    break;
            }
        }
        Query query = new Query(criteria);
        // query.addCriteria(criteria);
        return query;
    }

    private Update getUpdater(Map<String, Object> setMap) {

        Update updater = null;
        for (String key : setMap.keySet()) {
            Object obj = setMap.get(key);// 得到每个key对用value的值
            if (updater == null) {
                updater = Update.update(key, obj);
            } else {
                updater = updater.set(key, obj);
            }
        }

        return updater;
    }
}