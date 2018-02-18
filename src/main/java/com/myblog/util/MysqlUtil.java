package com.myblog.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.myblog.common.Common;
import com.myblog.common.Config;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/4 10:50
 * Description: MySql使用Quartz自动备份
 */
@Component("mysqlService")
public class MysqlUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(MysqlUtil.class);

    public static void main(String[] args) {
        new MysqlUtil().exportDataBase();
    }

    /**
     * export database;
     */
    public void exportDataBase() {
        logger.info("start backup database");
        String username = Config.getProperty("jdbc.username_dev");
        String password = Config.getProperty("jdbc.password_dev");
        String database = Config.getProperty("jdbc.database");
        String host = Config.getProperty("jdbc.host_dev");
        String os = System.getProperty("os.name");
        String file_path = null;
//        if (os.toLowerCase().startsWith("win")) {       //根据系统类型
//            file_path = System.getProperty("user.dir") + "\\sql\\";
//        } else {
//            file_path = System.getProperty("user.dir") + "/sql/";//保存的路径
//        }
        file_path = System.getProperty("myblog.path") + "sql";
        String file_name = "/myblog" + DateTime.now().toString("yyyyMMddHHmmss") + ".sql";
        String file = file_path + file_name;
        logger.info("file_path and file_name: " + file);
        //server
        String s_host = Config.getProperty("server.host");
        Integer s_port = Config.getIntProperty("server.port");
        String s_username = Config.getProperty("server.username");
        String s_password = Config.getProperty("server.password");
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(Common.MYSQL_DUMP).append(" -u ").append(username).append(" -p").append(password).append(" -h ").append(host).append(" ").append(database).append(" >").append(file);
            String sql = sb.toString();
            logger.info(sql);
            //connect to server
            Connection connection = new Connection(s_host, s_port);
            connection.connect();
            boolean isAuth = connection.authenticateWithPassword(s_username, s_password);
            if (!isAuth) {
                logger.error("server login error");
            }
            Session session = connection.openSession();
            session.execCommand(sql);
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            session.close();
            connection.close();
            stdout.close();
            br.close();
            logger.info("backup finish");
            logger.info(sb.toString());
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}