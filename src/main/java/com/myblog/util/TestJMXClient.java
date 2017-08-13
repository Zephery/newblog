package com.myblog.util;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/12 13:50
 * Description: 远程连接的时候，在服务器上用hostname -i查看是否为127.0.0.1，如果是，则要配置-Djava.rmi.server.hostname=your ip address。
 * 在catalina.sh前面配置
 * JAVA_OPTS="$JAVA_OPTS -Djava.rmi.server.hostname=123.206.28.24
 * -Dcom.sun.management.jmxremote.port=8888
 * -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false"
 */
public class TestJMXClient {
    public static void main(String[] args) {
        try {

            String jmxURL = "service:jmx:rmi:///jndi/rmi://123.206.28.24:8888/jmxrmi";
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            Map map = new HashMap();
            String[] credentials = new String[]{"monitorRole", "tomcat"};
            map.put("jmx.remote.credentials", credentials);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL,
                    map);
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();

            // 端口最好是动态取得
            ObjectName threadObjName = new ObjectName(
                    "Catalina:type=GlobalRequestProcessor,name=\"http-nio-8080\"");
            MBeanInfo mbInfo = mbsc.getMBeanInfo(threadObjName);

            String attrName = "currentThreadCount";// tomcat的线程数对应的属性值
            MBeanAttributeInfo[] mbAttributes = mbInfo.getAttributes();
            System.out.println("currentThreadCount:"
                    + mbsc.getAttribute(threadObjName, attrName));

            // heap
//            for (int j = 0; j < mbsc.getDomains().length; j++) {
//                System.out.println("###########" + mbsc.getDomains()[j]);
//            }
            Set MBeanset = mbsc.queryMBeans(null, null);
            System.out.println("MBeanset.size() : " + MBeanset.size());

            // ------------------------- system ----------------------
            ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");
            System.out.println("厂商:"
                    + (String) mbsc.getAttribute(runtimeObjName, "VmVendor"));
//            System.out.println(mbsc.);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}