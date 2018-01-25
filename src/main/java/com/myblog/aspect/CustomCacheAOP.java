package com.myblog.aspect;

import com.myblog.common.CustomCacheAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author Zephery
 * @since 2018/1/25 12:26
 */
@Aspect
public class CustomCacheAOP {
    ThreadLocal<Long> time = new ThreadLocal<>();
    ThreadLocal<String> tag = new ThreadLocal<>();
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Pointcut("@annotation(com.myblog.common.CustomCacheAnnotation)")
    public void getCache() {
    }

    /**
     * 在所有标注@getCache的地方切入
     *
     * @param joinPoint
     */
    @Before("getCache()")
    public void beforeExec(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String ActionName = method.getAnnotation(CustomCacheAnnotation.class).key();
        String fieldList = method.getAnnotation(CustomCacheAnnotation.class).condition();
    }

    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}