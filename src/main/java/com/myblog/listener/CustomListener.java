package com.myblog.listener;

import com.myblog.util.IPUtils;
import com.myblog.util.WinOrLinux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

/**
 * @author Zephery
 * @since 2017/11/4 11:58
 * Description:
 */
public class CustomListener implements ServletContextListener {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("myblog start,begin to record the local server ip");
        try {
            String userDir = this.getClass().getClassLoader().getResource("").getPath();
            File file1 = new File(userDir);
            String str = file1.getParent();
            File file2 = new File(str);
            String abPath = WinOrLinux.isWin() ? file2.getParent() + "\\foot.jsp" : file2.getParent() + "/foot.jsp";
            String ip = IPUtils.getServerIp();
            File file = new File(abPath);

//            Document doc = Jsoup.parse(file, "utf-8").outputSettings(new Document.OutputSettings().prettyPrint(false));
//            Document doc = new Document(abPath).outputSettings(new Document.OutputSettings().prettyPrint(false));
//            Element element = doc.select("#serverIp").first();
//            element.text(ip);
//            String newStr = doc.toString();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(abPath), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            StringBuilder result = new StringBuilder();
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(s);
                result.append("\r\n");
            }
            br.close();
            String newStr = result.toString().replace("serverIp", ip);


            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            outputWriter.write(newStr);
            outputWriter.close();
            fileOutputStream.close();

//            FileOutputStream out = new FileOutputStream(abPath);
//            out.write(newStr.getBytes("utf-8"));
//            out.close();
            System.out.println();
        } catch (Exception e) {
            logger.error("record ip error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}