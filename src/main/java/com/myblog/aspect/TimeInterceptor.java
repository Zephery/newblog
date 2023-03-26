package com.myblog.aspect;

import com.myblog.service.IAsyncService;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author Zephery
 * @since 2018/1/13 13:34
 */
@Order(1)
@Aspect
@Component
public class TimeInterceptor {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);
    @Resource
    private IAsyncService asyncService;

    //切点
    @Pointcut("execution(* com.myblog.service.impl.BlogServiceImpl.*(..))")
    public void pointcut() {
    }

    /**
     * 前置通知
     *
     * @param jp
     */
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        logger.info(jp.getSignature().getName());
        logger.info("----------前置通知----------");
    }

    /**
     * 最终通知
     *
     * @param jp
     */
    @After("pointcut()")
    public void after(JoinPoint jp) {
        logger.info("----------最终通知----------");
    }

    /**
     * 环绕通知
     * 统计方法执行耗时Around
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* (com.myblog.service.impl.*+&&!com.myblog.service.impl.AsyncServiceImpl).*(..))")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

//		try {
        obj = joinPoint.proceed(args);
//		} catch (Throwable e) {
//			logger.error("统计某方法执行耗时环绕通知出错", e);
//		}

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);

        return obj;
    }

    /**
     * 返回结果通知
     *
     * @param jp
     * @param result
     */
    @AfterReturning(pointcut = "execution(* com.myblog.service.impl.BlogServiceImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        logger.info(jp.getSignature().getName());
        logger.info("结果是：" + result);
        logger.info("----------返回结果----------");
    }

    /**
     * 异常后通知
     *
     * @param jp
     * @param exp
     */
    @AfterThrowing(pointcut = "execution(* com.myblog.service.impl.BlogServiceImpl.*(..))", throwing = "exp")
    public void afterThrowing(JoinPoint jp, Exception exp) {
        logger.info(jp.getSignature().getName());
        logger.info("异常消息：" + exp.getMessage());
        logger.info("----------异常通知----------");
    }

    /**
     * 打印方法执行耗时的信息
     *
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
//		if (diffTime > ONE_MINUTE) {
        logger.info(methodName + " method take time: " + diffTime + " ms");
//		}
        asyncService.insertMethodTime(methodName, endTime - startTime);
    }
}