package com.myblog.client;

import com.myblog.common.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2018/5/9 23:07
 * Description:
 */
@Slf4j
public class ESClient {
    private static RestClient restClient;

    public static RestClient getClient() {
        if (restClient == null) {
            synchronized (ESClient.class) {
                if (restClient == null) {
                    restClient = createRestClient();
                }
            }
        }
        return restClient;
    }

    private static RestClient createRestClient() {
        return RestClient.builder(new HttpHost(Config.getProperty("es.host"), Config.getIntProperty("es.port"))).build();

    }
}