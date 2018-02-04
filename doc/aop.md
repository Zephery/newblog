# 一、概述
在通常的开发过程中，我们调用的顺序通常是controller->service-dao，其中，service中包含着太多的业务逻辑，并且还要不断调用dao来实现自身的业务逻辑，经常会导致业务耗时过久，在aop出现之前，方式一般是在函数中开始写一个startTime，结尾再写一个endTime来查看执行该函数的耗时，过多的使用此类方式会导致代码的耦合性太高，不利于管理，于是，AOP（面向切面）出现了。AOP关注的是横向的，而OOP的是纵向。
<div align="center">![](http://image.wenzhihuai.com/images/20180118085015.png)</div>


Spring自2.0版本开始采用@AspectJ注解非常容易的定义一个切面。@AspectJ注解使用AspectJ切点表达式语法进行切点定义，可以通过切点函数、运算符、通配符等高级功能进行切点定义，拥有强大的连接点描述能力。
## 1.1 特点
AOP（Aspect Oriented Programming）面向切面编程，通过预编译方式和运行期动态代理实现程序功能的横向多模块统一控制的一种技术。AOP是OOP的补充，是Spring框架中的一个重要内容。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。AOP可以分为静态织入与动态织入，静态织入即在编译前将需织入内容写入目标模块中，这样成本非常高。动态织入则不需要改变目标模块。Spring框架实现了AOP，使用注解配置完成AOP比使用XML配置要更加方便与直观。
## 1.2 AOP概述
**Aspect**:一个模块用来关注多个类的切面。在JAVA EE的应用中，事务是AOP的典型例子。
**Joinpoint(连接点)**:所谓连接点是指那些被拦截到的点。在spring中,这些点指的是方法,因为spring只支持方法类型的连接点.
**Pointcut(切入点)**:所谓切入点是指我们要对哪些Joinpoint进行拦截的定义.
**Advice(通知/增强)**:所谓通知是指拦截到Joinpoint之后所要做的事情就是通知.通知分为前置通知,后置通知,异常通知,最终通知,环绕通知(切面要完成的功能)
**Introduction(引介)**:引介是一种特殊的通知在不修改类代码的前提下, Introduction可以在运行期为类动态地添加一些方法或Field.
**Target(目标对象)**:代理的目标对象
**Weaving(织入)**:是指把增强应用到目标对象来创建新的代理对象的过程.spring采用动态代理织入，而AspectJ采用编译期织入和类装在期织入.
**Proxy（代理）**:一个类被AOP织入增强后，就产生一个结果代理类Aspect(切面): 是切入点和通知（引介）的结合

# 二、Spring中的AOP
Spring实现AOP主要是由IOC容器来负责生成、管理的。其创建的方式有两种：
1. 默认使用Java动态代理来创建AOP代理；
2. 当需要代理的类不是代理接口的时候，Spring会切换为使用CGLIB代理，也可强制使用CGLIB。高版本的Spring会自动选择是使用动态代理还是CGLIB生成代理内容，当然我们也可以强制使用CGLIB生成代理，那就是<aop:config>里面有一个"proxy-target-class"属性，这个属性值如果被设置为true，那么基于类的代理将起作用。

## 2.1 AspectJ支持5种类型的通知注解：
[1] Before：前置通知，在方法执行之前执行
[2] After：后置通知，在方法执行之后执行
[3] AfterRunning：返回通知，在方法返回结果之后执行
[4] AfterThrowing：异常通知，在方法抛出异常之后执行
[5] Around：环绕通知，围绕着方法执行
其中，环绕通知是最常见的一种通知注解，特别是在缓存的使用中，例如：Spring-Cache中的使用，在service的方法中添加一个cache的注解，通过AOP来拦截，如果缓存中已经存在，则直接返回结果，如果没有，再进行service的访问。
## 2.2 Spring提供了4种实现AOP的方式：
1. 经典的基于代理的AOP
2. @AspectJ注解驱动的切面
3. 纯POJO切面
4. 注入式AspectJ切面
    

# 三、原理概述
Spring AOP的实现原理是基于动态织入的动态代理技术，而AspectJ则是静态织入，而动态代理技术又分为Java JDK动态代理和CGLIB动态代理，前者是基于反射技术的实现，后者是基于继承的机制实现。Spring AOP 在使用时机上也进行自动化调整，当有接口时会自动选择JDK动态代理技术，如果没有则选择CGLIB技术，当然Spring AOP的底层实现并没有这么简单，为更简便生成代理对象，Spring AOP 内部实现了一个专注于生成代理对象的工厂类，这样就避免了大量的手动编码，这点也是十分人性化的，但最核心的还是动态代理技术。从性能上来说，Spring AOP 虽然无需特殊编译器协助，但性能上并不优于AspectJ的静态织入，这点了解一下即可。
<div align="center">![](http://image.wenzhihuai.com/images/20180204060518.png)</div>

具体的原理请看[Spring AOP](http://blog.csdn.net/javazejian/article/details/56267036/)



# 四、使用
网上看别人写了很多入门的例子，自己就不再阐述了，毕竟自己还是菜，下面是关于AOP入门的资料：
[我们为什么要使用AOP？](http://www.cnblogs.com/xrq730/p/7003082.html)
[Spring中AOP的实现](http://blog.csdn.net/u014292162/article/details/52504633)
[关于AOP](http://blog.csdn.net/javazejian/article/details/56267036/)

下面是自己在[个人网站](http://www.wenzhihuai.com)中的使用，主要是用来统计一个方法的执行消耗了多少时间，需要引入aopalliance.jar、aspectj.weaver.jar 和 spring-aspects.jar的包。
### 4.1 在Spring MVC中开启AOP
```html
    <!--自动扫描自定义切面-->
    <aop:aspectj-autoproxy/>
```
### 4.2 定义一个切面
```html
/**
 * 可以使用 @Order 注解指定切面的优先级, 值越小优先级越高
 */
@Order(2)
@Aspect
@Component
public class TimeInterceptor {
}
```
### 4.3 声明一个切入点
```html
    @Pointcut("execution(* com.myblog.service.impl.BlogServiceImpl.*(..))")
    public void pointcut() {
    }
```
### 4.4 声明一个前置切点
```html
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        logger.info(jp.getSignature().getName());
        logger.info("----------前置通知----------");
    }
```
### 4.5 声明一个后置切点
```html
    @After("pointcut()")
    public void after(JoinPoint jp) {
        logger.info("----------最终通知----------");
    }

```
### 4.6 环绕通知
这里，特别要注意的是要抛出Throwable异常，否则方法执行报错的时候无法处理也无法查看
```html
    @Around("execution(* (com.myblog.service.impl.*+&&!com.myblog.service.impl.AsyncServiceImpl).*(..))")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        obj = joinPoint.proceed(args);
        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);
        return obj;
    }
```
### 4.7 返回结果通知
```html
    @AfterReturning(pointcut = "execution(* com.myblog.service.impl.BlogServiceImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        logger.info(jp.getSignature().getName());
        logger.info("结果是：" + result);
        logger.info("----------返回结果----------");
    }
```
### 4.8 异常后通知
```html
    @AfterThrowing(pointcut = "execution(* com.myblog.service.impl.BlogServiceImpl.*(..))", throwing = "exp")
    public void afterThrowing(JoinPoint jp, Exception exp) {
        logger.info(jp.getSignature().getName());
        logger.info("异常消息：" + exp.getMessage());
        logger.info("----------异常通知----------");
    }
```
### 4.9 结果
```html
2018-02-04  17:22:46.287 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - getAllBlog
2018-02-04  17:22:46.288 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - ----------前置通知----------
2018-02-04  17:22:46.288 [http-nio-9090-exec-3] DEBUG com.myblog.dao.BlogMapper - Cache Hit Ratio [com.myblog.dao.BlogMapper]: 0.6
2018-02-04  17:22:46.288 [http-nio-9090-exec-3] DEBUG com.myblog.dao.BlogMapper - Cache Hit Ratio [com.myblog.dao.BlogMapper]: 0.6666666666666666
2018-02-04  17:22:46.289 [http-nio-9090-exec-3] INFO  com.myblog.cache.EhRedisCache - ===========================Cache L1 (ehcache) 
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - com.myblog.service.IBlogService.getAllBlog method take time: **5 ms**
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - ----------最终通知----------
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - getAllBlog
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - 结果是：Page{count=true, pageNum=1, pageSize=15, startRow=0, endRow=15, total=462, pages=31, countSignal=false, orderBy='null', orderByOnly=false, reasonable=true, pageSizeZero=true}
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.aspect.TimeInterceptor - ----------返回结果----------
2018-02-04  17:22:46.292 [http-nio-9090-exec-3] INFO  com.myblog.cache.EhRedisCache - ===========================Cache L1 (ehcache) :{myCache}{com.myblog.service.impl.BlogServiceImpl.getBanner}={[ key = com.myblog.service.impl.BlogServiceImpl.getBanner, value=[com.myblog.model.Blog@2a5de6bc, com.myblog.model.Blog@544159b3, com.myblog.model.Blog@1de1421c, com.myblog.model.Blog@6dbb79bb, com.myblog.model.Blog@28160ab6], version=1, hitCount=2, CreationTime = 1517736161430, LastAccessTime = 1517736166292 ]}
```
由结果可以看到，整个方法的执行耗时5ms，算是客观吧，如果太大则要对其进行优化。

主要的源码在这：

[TimeInterceptor](https://github.com/Zephery/newblog/blob/master/src/main/java/com/myblog/aspect/TimeInterceptor.java)

也可以下载我的博客源码参考参考：
[newblog](https://github.com/Zephery/newblog)



# 参考
1. [Spring学习总结——Spring实现AOP的多种方式](http://www.cnblogs.com/best/p/5736422.html)
2. [Spring AOP基础入门总结一](https://www.cnblogs.com/wang-meng/p/5641549.html#top)
3. [Spring AOP官方](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html)