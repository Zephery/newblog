package com.myblog.dao;

import com.myblog.model.CriteriaMap;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Zephery
 * @since 2017/11/19 18:49
 */
public interface IMongoDao {
    /**
     * 插入单个数据
     *
     * @param objectToSave
     * @param tableName
     */
    public void insert(Object objectToSave, String tableName);

    /**
     * 插入批量数据
     *
     * @param batchToSave
     * @param tableName
     */
    public void insertBath(Collection<? extends Object> batchToSave, String tableName);

    /**
     * 根据条件删除数据
     *
     * @param criteriaMap
     * @param tableName
     */
    public void deleteByMap(CriteriaMap criteriaMap, String tableName);

    /**
     * 删除所有数据
     *
     * @param tableName
     */
    public void deleteAll(String tableName);

    /**
     * 更新数据
     *
     * @param criteriaMap
     * @param setMap
     * @param tableName
     */
    public void update(CriteriaMap criteriaMap, Map<String, Object> setMap, String tableName);

    /**
     * 查询数据
     *
     * @param criteriaMap
     * @param c
     * @param tableName
     * @return
     */
    public <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> c, String tableName);

    /**
     * 查询数据，带指定起止行
     *
     * @param criteriaMap
     * @param c
     * @param start
     * @param limit
     * @param tableName
     * @return
     */
    public <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> c, int start,
                                 int limit, String tableName);

    /**
     * 查询数据，带分页
     *
     * @param criteriaMap
     * @param c
     * @param pageNo
     * @param pageSize
     * @param tableName
     * @return
     */
    public <T> List<T> findByMap4Page(CriteriaMap criteriaMap, Class<T> c, int pageNo, int pageSize, String tableName);

    /**
     * 查询所有数据
     *
     * @param c
     * @param tableName
     * @return
     */
    public <T> List<T> findAll(Class<T> c, String tableName);

    /**
     * 查询数据并修改，返回修改前的数据
     *
     * @param criteriaMap
     * @param setMap
     * @param c
     * @param tableName
     * @return
     */
    public <T> T findAndModify(CriteriaMap criteriaMap, Map<String, Object> setMap, Class<T> c,
                               String tableName);

    /**
     * 查询数据并删除，返回删除前的数据
     *
     * @param criteriaMap
     * @param c
     * @param tableName
     * @return
     */
    public <T> T findAndRemove(CriteriaMap criteriaMap, Class<T> c, String tableName);

    /**
     * 统计
     *
     * @param criteriaMap
     * @param tableName
     * @return
     */
    public long count(CriteriaMap criteriaMap, String tableName);

    /**
     * spring data mongo  原生接口
     *
     * @return
     */
    public MongoTemplate getMongoTemplate();

}