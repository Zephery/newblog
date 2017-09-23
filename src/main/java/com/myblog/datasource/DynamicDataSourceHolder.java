package com.myblog.datasource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/9/23 12:00
 * Description:
 */
public class DynamicDataSourceHolder {
    /**
     * 注意：数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
     */
    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<String>();

    public static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    public static void setDataSource(String dataSource) {
        THREAD_DATA_SOURCE.set(dataSource);
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }

}