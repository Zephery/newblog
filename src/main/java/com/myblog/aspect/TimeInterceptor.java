package com.myblog.aspect;

import com.myblog.service.IAsyncService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Zephery
 * @since 2018/1/13 13:34
 */
@Aspect
@Component
public class TimeInterceptor {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);
    // 一分钟，即60000ms
    private static final long ONE_MINUTE = 60000;
    @Resource
    private IAsyncService asyncService;

    /**
     * 统计方法执行耗时Around环绕通知
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