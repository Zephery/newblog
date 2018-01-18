import com.myblog.model.Blog;
import com.myblog.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * @author Zephery
 * @since 2018/1/17 15:12
 */
public class Test {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void init() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:spring-test.xml");
        IBlogService blogService = (IBlogService) ctx.getBean("blogService");
        logger.info("start");
        List<Blog> blogs = blogService.getBanner();
        logger.info("end");
    }
}