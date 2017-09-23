package com.myblog.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/23 12:00
 * Description:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 从自定义的位置获取数据源标识
        return DynamicDataSourceHolder.getDataSource();
    }

}