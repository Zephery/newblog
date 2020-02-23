//package com.myblog.listener;
//
//import com.myblog.util.IPUtils;
//import com.myblog.util.WinOrLinux;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import java.io.*;
//
///**
// * @author Zephery
// * @since 2017/11/4 11:58
// * Description:
// */
//public class CustomListener implements ServletContextListener {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);
//
//    /**
//     * 记录服务器IP和项目启动的时间
//     *
//     * @param servletContextEvent
//     */
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        logger.info("myblog start,begin to record the local server ip");
//        try {
//
//
//            String user = System.getProperty("myblog.path");
//            //下面这个也行，而且简单
////            String user1=servletContextEvent.getServletContext().getRealPath("/");
//            String abPath = WinOrLinux.isWin() ? user + "foot.jsp" : user + "foot.jsp";
//            String ip = IPUtils.getServerIp();
//            File file = new File(abPath);
//
//
////            Document doc = Jsoup.parse(file, "utf-8").outputSettings(new Document.OutputSettings().prettyPrint(false));
////            Document doc = new Document(abPath).outputSettings(new Document.OutputSettings().prettyPrint(false));
////            Element element = doc.select("#serverIp").first();
////            element.text(ip);
////            String newStr = doc.toString();
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(abPath), "UTF-8"));//构造一个BufferedReader类来读取文件
//            String s = null;
//            StringBuilder result = new StringBuilder();
//            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                result.append(s);
//                result.append("\r\n");
//            }
//            br.close();
//            //record ip and project start time
//            String newStr = result.toString().replaceAll("serverIp", ip).replaceAll("projectStartTime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
//            outputWriter.write(newStr);
//            outputWriter.close();
//            fileOutputStream.close();
//
////            FileOutputStream out = new FileOutputStream(abPath);
////            out.write(newStr.getBytes("utf-8"));
////            out.close();
//        } catch (Exception e) {
//            logger.error("record ip error", e);
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}