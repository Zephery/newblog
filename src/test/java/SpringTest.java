//import com.myblog.service.IBlogService;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
///**
// * @author Zephery
// * @since 2018/1/17 15:12
// */
//public class SpringTest {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);
//    private ApplicationContext ctx;
//
//    @Before
//    public void before() {
//        ctx = new FileSystemXmlApplicationContext("classpath:spring-test.xml");
//    }
//
//    @Test
//    public void cacheTest() {
//        IBlogService blogService = (IBlogService) ctx.getBean("blogService");
//
//        long startTime = System.currentTimeMillis();
//        try {
//            Thread.sleep(5 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 100; i++) {
//            blogService.getBlogDetail(615);
//        }
//        System.out.print("========使用了缓存=======");
//        System.out.println(System.currentTimeMillis() - startTime);
//    }
//
//    @Test
//    public void aop() {
//        IBlogService blogService = (IBlogService) ctx.getBean("blogService");
//        blogService.getBlogDetail(615);
//    }
//}