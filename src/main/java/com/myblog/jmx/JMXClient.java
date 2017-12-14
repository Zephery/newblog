package com.myblog.jmx;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myblog.common.Config;
import com.myblog.util.JedisUtil;
import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.OperatingSystemMXBean;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/12 13:50
 * Description: 远程连接的时候，在服务器上用hostname -i查看是否为127.0.0.1，如果是，则要
 * 配置-Djava.rmi.server.hostname=your ip address。
 * 在catalina.sh前面配置
 * JAVA_OPTS="$JAVA_OPTS -Djava.rmi.server.hostname=47.95.10.139
 * -Dcom.sun.management.jmxremote.port=8888
 * -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false"
 */
@Service("jmxclient")
public class JMXClient {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(JMXClient.class);
    private static MBeanServerConnection mbsconnector = null;
    private static JMXClient jmxUtil = null;

    static {
        mbsconnector = initMBeanServerConnection();
    }

    public JMXClient() {
        initMBeanServerConnection();
    }

    public static JMXClient getInstance() {
        if (jmxUtil == null) {
            jmxUtil = createInstance();
        }
        return jmxUtil;
    }

    private synchronized static JMXClient createInstance() {
        if (jmxUtil == null) {
            jmxUtil = new JMXClient();
        }
        return jmxUtil;
    }

    private static MBeanServerConnection initMBeanServerConnection() {
        try {
            String ip = Config.getProperty("jmx_ip");
            String port = Config.getProperty("jmx_port");
            String jmxURL = "service:jmx:rmi:///jndi/rmi://" + ip + ":" + port + "/jmxrmi";
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            Map map = new HashMap();
            String[] credentials = Config.getProperty("credentials").split(",");
            map.put("jmx.remote.credentials", credentials);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL, map);
            mbsconnector = connector.getMBeanServerConnection();
        } catch (IOException e) {
            logger.error("get connector error" + e);
        }
        return mbsconnector;
    }

    public static void main(String[] args) throws Exception {
    }

    public Long getJVMUsage() {
        MemoryMXBean memBean = null;
        try {
            memBean = ManagementFactory.newPlatformMXBeanProxy
                    (mbsconnector, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
            MemoryUsage heap = memBean.getHeapMemoryUsage();
            return heap.getUsed();
        } catch (IOException e) {
            logger.error("", e);
            return 0L;
        }
    }

    /**
     * 定时任务
     */
    public void quartzjob() {
        try {
            DecimalFormat df = new java.text.DecimalFormat("#.00");
            //cpu usage
            double ratio = 0;
            OperatingSystemMXBean opMXbean = ManagementFactory.newPlatformMXBeanProxy(mbsconnector,
                    ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
            Long start = System.currentTimeMillis();
            long startT = opMXbean.getProcessCpuTime();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }
            Long end = System.currentTimeMillis();
            long endT = opMXbean.getProcessCpuTime();
            ratio = (endT - startT) / 1000000.0 / (end - start) / opMXbean.getAvailableProcessors();
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            Long cpu_length = JedisUtil.getInstance().llen("cpu_usage");
            JedisUtil.getInstance().lpush("cpu_usage", df.format(ratio * 100));
            if (cpu_length > 50) {
                JedisUtil.getInstance().rpop("cpu_usage");
            }
            lock.unlock();
            //jvm usage
            MemoryMXBean memBean = ManagementFactory.newPlatformMXBeanProxy
                    (mbsconnector, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
            MemoryUsage heap = memBean.getHeapMemoryUsage();
            ReentrantLock lock2 = new ReentrantLock();
            lock2.lock();
            Long jmx_memory_use_length = JedisUtil.getInstance().llen("jmx_memory_use");
            JedisUtil.getInstance().lpush("jmx_memory_use", String.valueOf(heap.getUsed() / 1048576));
            JedisUtil.getInstance().lpush("jmx_memory_time", DateTime.now().toString("HH:mm:ss"));
            if (jmx_memory_use_length > 50) {
                JedisUtil.getInstance().rpop("jmx_memory_use");
                JedisUtil.getInstance().rpop("jmx_memory_time");
            }
            JedisUtil.getInstance().set("jmx_memory_committed", String.valueOf(heap.getCommitted() / 1048576));
            lock2.unlock();
        } catch (Exception e) {
            logger.error("quartzjob error", e);
        }
    }

    public float gc() {
        try {
            ObjectName gcName = new ObjectName(ManagementFactory.GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE + ",*");
            Long ttt = 0L;
            for (ObjectName name : mbsconnector.queryNames(gcName, null)) {
                GarbageCollectorMXBean gc = ManagementFactory.newPlatformMXBeanProxy(mbsconnector, name.getCanonicalName(), GarbageCollectorMXBean.class);
//                System.out.println(gc.getName());
//                ttt += gc.getCollectionTime();
                System.out.println(gc.getLastGcInfo());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return 1.1F;
    }

    public JsonArray getMemoryPoolDetail() {
        List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
        JsonArray array = new JsonArray();
        for (MemoryPoolMXBean mp : mps) {
            if (mp.getName().contains("Eden") || mp.getName().contains("Survivor")  //win与linux上的不同，顾使用contains
                    || mp.getName().contains("Tenured") || mp.getName().contains("Old")) {
                array.add(getMpJsonObject(mp));
            }
        }
        return array;
    }

    private JsonObject getMpJsonObject(MemoryPoolMXBean mp) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", mp.getName().replaceAll("PS ", ""));
        List<Long> array = new ArrayList<>();
        JsonParser parser = new JsonParser();
        array.add(mp.getCollectionUsage().getInit() / 1048576);
        array.add(mp.getCollectionUsage().getUsed() / 1048576);
        array.add(mp.getCollectionUsage().getCommitted() / 1048576);
        array.add(mp.getCollectionUsage().getMax() / 1048576);
        jsonObject.add("data", parser.parse(array.toString()));
        return jsonObject;
    }

    public String getCpuUsage() {
        double ratio = 0;
        DecimalFormat df = new java.text.DecimalFormat("#.00");

        try {
            OperatingSystemMXBean opMXbean = ManagementFactory.newPlatformMXBeanProxy(mbsconnector,
                    ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
            Long start = System.currentTimeMillis();
            long startT = opMXbean.getProcessCpuTime();
            try {
                TimeUnit.SECONDS.sleep(1);       //导致速度慢
            } catch (InterruptedException e) {
                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }
            Long end = System.currentTimeMillis();
            long endT = opMXbean.getProcessCpuTime();
            ratio = (endT - startT) / 1000000.0 / (end - start) / opMXbean.getAvailableProcessors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return df.format(ratio * 100);
    }
}