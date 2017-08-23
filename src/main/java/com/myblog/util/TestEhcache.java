package com.myblog.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/23 14:18
 * Description:
 */
public class TestEhcache {
    @Test
    public void testEhcache() {
        // Creating a CacheManager based on a specified configuration file.
        CacheManager manager = CacheManager.newInstance("src/main/resources/ehcache.xml");
        // obtains a Cache called sampleCache1, which has been preconfigured in the configuration file
        Cache cache = manager.getCache("sampleCache1");
        // puts an element into a cache
        Element element = new Element("key1", "哈哈");
        cache.put(element);
        //The following gets a NonSerializable value from an element with a key of key1.
        Object value = element.getObjectValue();
        System.out.println(value.toString());
        manager.shutdown();
    }
}