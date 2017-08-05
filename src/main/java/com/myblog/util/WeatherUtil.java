package com.myblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/5 12:54
 * Description:java执行python脚本，极其错误的想法，地点改变怎么办
 */
public class WeatherUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(WeatherUtil.class);
    private static final String BASE_URL = "http://www.seniverse.com/weather/weather.aspx?uid=U35799536E&cid=CH" +
            "BJ000000&l=&p=SMART&a=1&u=C&s=13&m=%200&x=1&d=0&fc=&bgc=2E93D9&bc=&ti=0&in=1&li=";
    private static final String os = System.getProperty("os.name");

    /**
     * use phantomjs to get a picture from weather
     */
    public static void execute_phantomjs() {
//        try {
//            System.setProperty("phantomjs.binary.path", "D:\\Program Files\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//            WebDriver driver = new PhantomJSDriver();
//            driver.manage().window().setSize(new Dimension(330, 115));
////            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐试等待
//            driver.get(BASE_URL);
//            TimeUnit.SECONDS.sleep(10);
//            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            if (os.toLowerCase().startsWith("win")) {       //根据系统类型
//                FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\images\\weather.png"));
//            } else {
//                FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/src/main/webapp/images/weather.png"));
//
//            }
//            driver.close();//关闭浏览器              System.out.println(driver.getWindowHandle());
//        } catch (Exception e) {
//            logger.error("selenium error", e);
//        }
    }

    public static void main(String[] args) {
        execute_phantomjs();
    }

}