package com.myblog.JMX;

import com.myblog.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/12 13:50
 * Description: 远程连接的时候，在服务器上用hostname -i查看是否为127.0.0.1，如果是，则要
 * 配置-Djava.rmi.server.hostname=your ip address。
 * 在catalina.sh前面配置
 * JAVA_OPTS="$JAVA_OPTS -Djava.rmi.server.hostname=123.206.28.24
 * -Dcom.sun.management.jmxremote.port=8888
 * -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false"
 */
public class JMXClient {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(JMXClient.class);
    private MBeanServerConnection mbsconnector = null;
    private static JMXClient jmxUtil = null;

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

    public JMXClient() {
        initMBeanServerConnection();
    }

    private void initMBeanServerConnection() {
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
    }

    public void get() {
        try {
            RuntimeMXBean runtimeMXBean =
                    ManagementFactory.newPlatformMXBeanProxy(mbsconnector, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
            System.out.println(runtimeMXBean.getClassPath());
            System.out.println(runtimeMXBean.getVmName());
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private float getCpuUsage() {
        float cpuUsage = 0;

        try {
            RuntimeMXBean runtimeMXBean =
                    ManagementFactory.newPlatformMXBeanProxy(mbsconnector, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
            ObjectName operateObjectName = new ObjectName("java.lang:type=OperatingSystem");
            ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");
            long processCpuTime = Long.parseLong(mbsconnector.getAttribute(operateObjectName, "ProcessCpuTime").toString());

            int availableProcessors = Integer.parseInt(mbsconnector.getAttribute(operateObjectName, "AvailableProcessors").toString());

            long upTime = Long.parseLong(mbsconnector.getAttribute(runtimeObjName, "Uptime").toString());

            logger.info("===2======processCpuTime: " + processCpuTime);
            logger.info("===2=====upTime: " + upTime);
            Long prevUpTime = runtimeMXBean.getUptime();
            long prevProcessCpuTime = Long.parseLong(mbsconnector.getAttribute(operateObjectName, "ProcessCpuTime").toString());
            if (prevUpTime > 0L && upTime > prevUpTime) {
                logger.info("===3=======");
                long elapsedCpu = processCpuTime - prevProcessCpuTime;
                long elapsedTime = upTime - prevUpTime;

                // cpuUsage could go higher than 100% because elapsedTime
                // and elapsedCpu are not fetched simultaneously. Limit to
                // 99% to avoid Plotter showing a scale from 0% to 200%.
                cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
                logger.info("===2=====cpuUsage: " + cpuUsage);
            }
            logger.info("===4=======");
            prevUpTime = upTime;
            prevProcessCpuTime = processCpuTime;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cpuUsage;
    }

    public static void main(String[] args) {
        System.out.println(JMXClient.getInstance().getCpuUsage());
    }
}