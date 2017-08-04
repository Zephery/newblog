package com.myblog.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
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
 * Description:
 */
@Component("mysqlService")
public class MysqlUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(MysqlUtil.class);

    /**
     * export database;
     */
    public void exportDataBase() {
        logger.info("start backup database");
        String username = Config.getProperty("jdbc.username");
        String password = Config.getProperty("jdbc.password");
        String database = Config.getProperty("jdbc.database");
        String host = Config.getProperty("jdbc.host");
        String os = System.getProperty("os.name");
        String file_path = null;
        if (os.toLowerCase().startsWith("win")) {       //根据系统类型
            file_path = System.getProperty("user.dir") + "\\sql\\";
        } else {
            file_path = System.getProperty("user.dir") + "/sql/";//保存的路径
        }
        String file_name = "myblog" + DateTime.now().toString("yyyyMMddHHmmss") + ".sql";
        String file = file_path + file_name;
        logger.info("file_path and file_name: " + file);
        //server
        String s_host = Config.getProperty("server.host");
        String s_username = Config.getProperty("server.username");
        String s_password = Config.getProperty("server.password");
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("mysqldump -u " + username + " -p" + password + " -h " + host + " " +
                    database + " >" + file);
            String sql = sb.toString();
            logger.info(sql);
            //connect to server
            Connection connection = new Connection(s_host);
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
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}