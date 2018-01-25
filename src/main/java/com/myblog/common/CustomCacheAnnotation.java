package com.myblog.common;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Zephery
 * @since 2018/1/24 14:09
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomCacheAnnotation {

    @AliasFor("cacheNames")
    String[] value() default {};

    @AliasFor("value")
    String[] cacheNames() default {};

    String key() default "";

    String keyGenerator() default "";

    String cacheManager() default "";

    String cacheResolver() default "";

    String condition() default "";

    boolean allEntries() default false;

    String unless() default "";

    boolean sync() default false;

    boolean beforeInvocation() default false;
}