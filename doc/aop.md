# 一、概述
在通常的开发过程中，我们调用的顺序通常是controller->service-dao，其中，service中包含着太多的业务逻辑，并且还要不断调用dao来实现自身的业务逻辑，经常会导致业务耗时过久，在aop出现之前，方式一般是在函数中开始写一个startTime，结尾再写一个endTime来查看执行该函数的耗时，过多的使用此类方式会导致代码的耦合性太高，不利于管理，于是，AOP（面向切面）出现了。AOP关注的是横向的，而OOP的是纵向。
<div align="center">![](http://image.wenzhihuai.com/images/20180118085015.png)</div>


Spring自2.0版本开始采用@AspectJ注解非常容易的定义一个切面。@AspectJ注解使用AspectJ切点表达式语法进行切点定义，可以通过切点函数、运算符、通配符等高级功能进行切点定义，拥有强大的连接点描述能力。
## 1.1 特点
AOP（Aspect Oriented Programming）面向切面编程，通过预编译方式和运行期动态代理实现程序功能的横向多模块统一控制的一种技术。AOP是OOP的补充，是Spring框架中的一个重要内容。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。AOP可以分为静态织入与动态织入，静态织入即在编译前将需织入内容写入目标模块中，这样成本非常高。动态织入则不需要改变目标模块。Spring框架实现了AOP，使用注解配置完成AOP比使用XML配置要更加方便与直观。
## 1.2 专业术语
**Joinpoint(连接点)**:所谓连接点是指那些被拦截到的点。在spring中,这些点指的是方法,因为spring只支持方法类型的连接点.
**Pointcut(切入点)**:所谓切入点是指我们要对哪些Joinpoint进行拦截的定义.
**Advice(通知/增强)**:所谓通知是指拦截到Joinpoint之后所要做的事情就是通知.通知分为前置通知,后置通知,异常通知,最终通知,环绕通知(切面要完成的功能)
**Introduction(引介)**:引介是一种特殊的通知在不修改类代码的前提下, Introduction可以在运行期为类动态地添加一些方法或Field.
**Target(目标对象)**:代理的目标对象
**Weaving(织入)**:是指把增强应用到目标对象来创建新的代理对象的过程.spring采用动态代理织入，而AspectJ采用编译期织入和类装在期织入.
**Proxy（代理）**:一个类被AOP织入增强后，就产生一个结果代理类Aspect(切面): 是切入点和通知（引介）的结合

# 二、



AspectJ支持5种类型的通知注解：
[1]Before：前置通知，在方法执行之前执行
[2]After：后置通知，在方法执行之后执行
[3]AfterRunning：返回通知，在方法返回结果之后执行
[4]AfterThrowing：异常通知，在方法抛出异常之后执行
[5]Around：环绕通知，围绕着方法执行







[TimeInterceptor](https://github.com/Zephery/newblog/blob/master/src/main/java/com/myblog/aspect/TimeInterceptor.java)






# 参考
1. [Spring学习总结——Spring实现AOP的多种方式](http://www.cnblogs.com/best/p/5736422.html)
2. [Spring AOP基础入门总结一](https://www.cnblogs.com/wang-meng/p/5641549.html#top)
3. []
