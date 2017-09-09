package com.myblog.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/12 13:50
 * Description: 远程连接的时候，在服务器上用hostname -i查看是否为127.0.0.1，如果是，则要配置-Djava.rmi.server.hostname=your ip address。
 * 在catalina.sh前面配置
 * JAVA_OPTS="$JAVA_OPTS -Djava.rmi.server.hostname=47.95.10.139
 * -Dcom.sun.management.jmxremote.port=8888
 * -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false"
 */
public class TestJMXClient {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(TestJMXClient.class);

    public static void main(String[] args) {
        try {

            String jmxURL = "service:jmx:rmi:///jndi/rmi://47.95.10.139:8888/jmxrmi";
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            Map map = new HashMap();
            String[] credentials = new String[]{"monitorRole", "tomcat"};
            map.put("jmx.remote.credentials", credentials);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL,
                    map);
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();
            Set MBeanset = mbsc.queryMBeans(null, null);
            System.out.println("MBeanset.size() : " + MBeanset.size());

            // ------------------------- system ----------------------
            ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");
            System.out.println("厂商:"
                    + (String) mbsc.getAttribute(runtimeObjName, "VmVendor"));

            MemoryMXBean memBean = ManagementFactory.newPlatformMXBeanProxy
                    (mbsc, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
//获取远程opretingsystemmxbean
            OperatingSystemMXBean opMXbean =
                    ManagementFactory.newPlatformMXBeanProxy(mbsc,
                            ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }

            MemoryUsage heap = memBean.getHeapMemoryUsage();
            MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();
            long heapSizeUsed = heap.getUsed();//堆使用的大小
            long nonHeapSizeUsed = nonHeap.getUsed();
            long heapCommitedSize = heap.getCommitted();
            long nonHeapCommitedSize = nonHeap.getCommitted();
            Long start = System.currentTimeMillis();
            double startT = opMXbean.getSystemLoadAverage();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }
            Long end = System.currentTimeMillis();
            double endT = opMXbean.getSystemLoadAverage();
            System.out.println();
            RuntimeMXBean runtimeMXBean =
                    ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
            System.out.println(runtimeMXBean.getClassPath());
            System.out.println(runtimeMXBean.getVmVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}