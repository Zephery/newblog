# 一、概述  
## 1.1 缓存介绍

系统的性能指标一般包括响应时间、延迟时间、吞吐量，并发用户数和资源利用率等。在应用运行过程中，我们有可能在一次数据库会话中，执行多次查询条件完全相同的SQL，MyBatis提供了一级缓存的方案优化这部分场景，如果是相同的SQL语句，会优先命中一级缓存，避免直接对数据库进行查询，提高性能。
缓存常用语：
数据不一致性、缓存更新机制、缓存可用性、缓存服务降级、缓存预热、缓存穿透
可查看[Redis实战（一） 使用缓存合理性](http://blog.csdn.net/diyhzp/article/details/54892358)

## 1.2 本站缓存架构
从没有使用缓存，到使用mybatis缓存，然后使用了ehcache，再然后是mybatis+redis缓存。
<div align="center">![](http://image.wenzhihuai.com/images/20180121034503.png)</div>
步骤：
（1）用户发送一个请求到nginx，nginx对请求进行分发。
（2）请求进入controller，service，service中查询缓存，如果命中，则直接返回结果，否则去调用mybatis。
（3）mybatis的缓存调用步骤：二级缓存->一级缓存->直接查询数据库。
（4）查询数据库的时候，mysql作了主主备份。


# 二、Mybatis缓存

## 2.1 mybatis一级缓存
Mybatis的一级缓存是指Session回话级别的缓存，也称作本地缓存。一级缓存的作用域是一个SqlSession。Mybatis默认开启一级缓存。在同一个SqlSession中，执行相同的查询SQL，第一次会去查询数据库，并写到缓存中；第二次直接从缓存中取。当执行SQL时两次查询中间发生了增删改操作，则SqlSession的缓存清空。Mybatis 默认支持一级缓存，不需要在配置文件中配置。  
<div align="center">![](http://image.wenzhihuai.com/images/20180120015614.png)</div>

我们来查看一下源码的类图，具体的源码分析简单概括一下：SqlSession实际上是使用PerpetualCache来维护的，PerpetualCache中定义了一个HashMap<k,v>来进行缓存。  
（1）当会话开始时，会创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象，Executor对象中持有一个新的PerpetualCache对象；  
（2）对于某个查询，根据statementId,params,rowBounds来构建一个key值，根据这个key值去缓存Cache中取出对应的key值存储的缓存结果。如果命中，则返回结果，如果没有命中，则去数据库中查询，再将结果存储到cache中，最后返回结果。如果执行增删改，则执行flushCacheIfRequired方法刷新缓存。
（3）当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。
<div align="center">![](http://image.wenzhihuai.com/images/20180120022427.png)</div>

## 2.2 mybatis二级缓存
Mybatis的二级缓存是指mapper映射文件，为Application应用级别的缓存，生命周期长。二级缓存的作用域是同一个namespace下的mapper映射文件内容，多个SqlSession共享。Mybatis需要手动设置启动二级缓存。在同一个namespace下的mapper文件中，执行相同的查询SQL。实现二级缓存，关键是要对Executor对象做文章，Mybatis给Executor对象加上了一个CachingExecutor，使用了设计模式中的装饰者模式，
<div align="center">![](http://image.wenzhihuai.com/images/20180120030017.png)</div>

### 2.2.1 MyBatis二级缓存的划分
MyBatis并不是简单地对整个Application就只有一个Cache缓存对象，它将缓存划分的更细，即是Mapper级别的，即每一个Mapper都可以拥有一个Cache对象，具体如下：
a.为每一个Mapper分配一个Cache缓存对象（使用<cache>节点配置）；
b.多个Mapper共用一个Cache缓存对象（使用<cache-ref>节点配置）；

### 2.2.2 二级缓存的开启
在mybatis的配置文件中添加：
```xml
<settings>
   <!--开启二级缓存-->
    <setting name="cacheEnabled" value="true"/>
</settings>
```
然后再需要开启二级缓存的mapper.xml中添加(本站使用了LRU算法，时间为120000毫秒)：
```xml
    <cache eviction="LRU"
           type="org.apache.ibatis.cache.impl.PerpetualCache"
           flushInterval="120000"
           size="1024"
           readOnly="true"/>
```
### 2.2.3 使用第三方支持的二级缓存的实现
  MyBatis对二级缓存的设计非常灵活，它自己内部实现了一系列的Cache缓存实现类，并提供了各种缓存刷新策略如LRU，FIFO等等；另外，MyBatis还允许用户自定义Cache接口实现，用户是需要实现org.apache.ibatis.cache.Cache接口，然后将Cache实现类配置在<cache  type="">节点的type属性上即可；除此之外，MyBatis还支持跟第三方内存缓存库如Memecached、Redis的集成，总之，使用MyBatis的二级缓存有三个选择:
1. MyBatis自身提供的缓存实现；  
2. 用户自定义的Cache接口实现；  
3. 跟第三方内存缓存库的集成；  
具体的实现，可参照：[SpringMVC + MyBatis + Mysql + Redis(作为二级缓存) 配置](http://blog.csdn.net/xiadi934/article/details/50786293) 

MyBatis中一级缓存和二级缓存的组织如下图所示（图片来自[深入理解mybatis原理](http://blog.csdn.net/luanlouis/article/details/41390801)）：
<div align="center">![](http://image.wenzhihuai.com/images/20180120120015.png)</div>

## 2.3 Mybatis在分布式环境下脏读问题
（1）如果是一级缓存，在多个SqlSession或者分布式的环境下，数据库的写操作会引起脏数据，多数情况可以通过设置缓存级别为Statement来解决。  
（2）如果是二级缓存，虽然粒度比一级缓存更细，但是在进行多表查询时，依旧可能会出现脏数据。
（3）Mybatis的缓存默认是本地的，分布式环境下出现脏读问题是不可避免的，虽然可以通过实现Mybatis的Cache接口，但还不如直接使用集中式缓存如Redis、Memcached好。

下面将介绍使用Redis集中式缓存在个人网站的应用。



# 三、Redis缓存
Redis运行于独立的进程，通过网络协议和应用交互，将数据保存在内存中，并提供多种手段持久化内存的数据。同时具备服务器的水平拆分、复制等分布式特性，使得其成为缓存服务器的主流。为了与Spring更好的结合使用，我们使用的是Spring-Data-Redis。此处省略安装过程和Redis的命令讲解。
<div align="center">![](http://image.wenzhihuai.com/images/20180119110640.png)</div>

## 3.1 Spring Cache
Spring 3.1 引入了激动人心的基于注释（annotation）的缓存（cache）技术，它本质上不是一个具体的缓存实现方案（例如EHCache 或者 OSCache），而是一个对缓存使用的抽象，通过在既有代码中添加少量它定义的各种 annotation，即能够达到缓存方法的返回对象的效果。Spring 的缓存技术还具备相当的灵活性，不仅能够使用 **SpEL（Spring Expression Language）**来定义缓存的 key 和各种 condition，还提供开箱即用的缓存临时存储方案，也支持和主流的专业缓存例如 EHCache 集成。
下面是Spring Cache常用的注解：

（1）@Cacheable
@Cacheable 的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存


|  属性 | 介绍  |例子|
| ------------ | ------------ |----|
|  value | 缓存的名称，必选  | @Cacheable(value=”mycache”) 或者@Cacheable(value={”cache1”,”cache2”}   | 
| key  | 缓存的key，可选，需要按照SpEL表达式填写  | @Cacheable(value=”testcache”,key=”#userName”)   |
| condition  | 缓存的条件，可以为空，使用 SpEL 编写,只有为 true 才进行缓存 | @Cacheable(value=”testcache”,key=”#userName”)   |



（2）@CachePut
@CachePut 的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用

|  属性 | 介绍  |例子|
| ------------ | ------------ |----|
|  value | 缓存的名称，必选  | @Cacheable(value=”mycache”) 或者@Cacheable(value={”cache1”,”cache2”}   | 
| key  | 缓存的key，可选，需要按照SpEL表达式填写  | @Cacheable(value=”testcache”,key=”#userName”)   |
| condition  | 缓存的条件，可以为空，使用 SpEL 编写,只有为 true 才进行缓存 | @Cacheable(value=”testcache”,key=”#userName”)   |


（3）@CacheEvict 
@CachEvict 的作用 主要针对方法配置，能够根据一定的条件对缓存进行清空


|  属性 | 介绍  |例子|
| ------------ | ------------ |----|
|  value | 缓存的名称，必选  | @Cacheable(value=”mycache”) 或者@Cacheable(value={”cache1”,”cache2”}   | 
| key  | 缓存的key，可选，需要按照SpEL表达式填写  | @Cacheable(value=”testcache”,key=”#userName”)   |
| condition  | 缓存的条件，可以为空，使用 SpEL 编写,只有为 true 才进行缓存 | @Cacheable(value=”testcache”,key=”#userName”)   |
| allEntries  | 是否清空所有缓存内容，默认为false | @CachEvict(value=”testcache”,allEntries=true)   |
| beforeInvocation  | 是否在方法执行前就清空，缺省为 false | @CachEvict(value=”testcache”，beforeInvocation=true)   |


但是有个问题：
Spring官方认为：缓存过期时间由各个产商决定，所以并不提供缓存过期时间的注解。所以，如果想实现各个元素过期时间不同，就需要自己重写一下Spring cache。

## 3.2 引入包
一般是Spring常用的包+Spring data redis的包，记得注意去掉所有冲突的包，之前才过坑，Spring-data-MongoDB已经有SpEL的库了，和自己新引进去的冲突，搞得我以为自己是配置配错了，真是个坑，注意，开发过程中一定要去除掉所有冲突的包!!!

## 3.3 ApplicationContext.xml
需要启用缓存的注解开关，并配置好Redis。序列化方式也要带上，否则会碰到幽灵bug。
```xml
    <!-- 启用缓存注解开关，此处可自定义keyGenerator -->
    <cache:annotation-driven/>
    <bean id="jedisConnectionFactory"
          class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
        <property name="hostName" value="${host}"/>
        <property name="port" value="${port}"/>
        <property name="password" value="${password}"/>
        <property name="database" value="${redis.default.db}"/>
        <property name="timeout" value="${timeout}"/>
        <property name="poolConfig" ref="jedisPoolConfig"/>
        <property name="usePool" value="true"/>
    </bean>
    <bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
        <property name="connectionFactory" ref="jedisConnectionFactory"/>
        <!-- 序列化方式 建议key/hashKey采用StringRedisSerializer。 -->
        <property name="keySerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
        </property>
        <property name="hashKeySerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
        </property>
        <property name="valueSerializer">
            <bean class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
        </property>
        <property name="hashValueSerializer">
            <bean class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
        </property>
    </bean>
    <bean id="cacheManager" class="org.springframework.data.redis.cache.RedisCacheManager">
        <constructor-arg name="redisOperations" ref="redisTemplate" />
        <!--统一过期时间-->
        <property name="defaultExpiration" value="${redis.defaultExpiration}"/>
    </bean>
```

## 3.5 自定义KeyGenerator
在分布式系统中，很容易存在不同类相同名字的方法，如A.getAll(),B.getAll()，默认的key（getAll）都是一样的，会很容易产生问题，所以，需要自定义key来实现分布式环境下的不同。
```java
@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append(".");
        sb.append(method.getName());
        for (Object obj : objects) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
```
之后，存储的key就变为：com.myblog.service.impl.BlogServiceImpl.getBanner。

## 3.4 添加注解
在所需要的方法上添加注解，比如，首页中的那几张幻灯片，每次进入首页都需要查询数据库，这里，我们直接放入缓存里，减少数据库的压力，还有就是那些热门文章，访问量比较大的，也放进数据库里。

```java
    @Override
    @Cacheable(value = "getBanner", keyGenerator = "customKeyGenerator")
    public List<Blog> getBanner() {
        return blogMapper.getBanner();
    }
    @Override
    @Cacheable(value = "getBlogDetail", key = "'blogid'.concat(#blogid)")
    public Blog getBlogDetail(Integer blogid) {
        Blog blog = blogMapper.selectByPrimaryKey(blogid);
        if (blog == null) {
            return null;
        }
        Category category = categoryMapper.selectByPrimaryKey(blog.getCategoryid());
        blog.setCategory(category);
        List<Tag> tags = tagMapper.getTagByBlogId(blog.getBlogid());
        blog.setTags(tags.size() > 0 ? tags : null);
        asyncService.updatebloghits(blogid);//异步更新阅读次数
        logger.info("没有走缓存");
        return blog;
    }
```


## 3.5 测试
我们调用一个getBlogDetail（获取博客详情）100次来对比一下时间。连接的数据库在深圳，本人在广州，还是有那么一丢丢的网路延时的。
```java
public class SpringTest {
    @Test
    public void init() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:spring-test.xml");
        IBlogService blogService = (IBlogService) ctx.getBean("blogService");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            blogService.getBlogDetail(615);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
```
为了做一下对比，我们同时使用mybatis自身缓存来进行测试。

## 3.6 实验结果
统计出结果如下：
```html
没有使用任何缓存（mybatis一级缓存没有关闭）：18305
使用远程Redis缓存：12727
使用Mybatis缓存：6649
使用本地Redis缓存：5818
```
由结果看出，缓存的使用大大较少了获取数据的时间。


部署进个人博客之后，redis已经缓存的数据：
<div align="center">![](http://image.wenzhihuai.com/images/20180120052757.png)</div>



## 3.7 分页的数据怎么办
个人网站中共有两个栏目，一个是技术杂谈，另一个是生活笔记，每点击一次栏目的时候，会根据页数从数据库中查询数据，百度了下，大概有三种方法：
（1）以页码作为Key，然后缓存整个页面。
（2）分条存取，只从数据库中获取分页的文章ID序列，然后从service（缓存策略在service中实现）中获取。
第一种，由于使用了第三方的插件PageHelper，分页获取的话会比较麻烦，同时整页缓存对内存压力也蛮大的，毕竟服务器只有2g。第二条实现方式简单，缺陷是依旧需要查询数据库，想了想还是放弃了。缓存的初衷是对请求频繁又不易变的数据，实际使用中很少会反复的请求同一页的数据（查询条件也相同），当然对数据中某些字段做缓存还是有必要的。


# 四、如何解决脏读？
对于文章来说，内容是不经常更新的，没有涉及到缓存一致性，但是对于文章的阅读量，用户每点击一次，就应该更新浏览量的。对于文章的缓存，常规的设计是将文章存储进数据库中，然后读取的时候放入缓存中，然后将浏览量以文章ID+浏览量的结构实时的存入redis服务器中。本站当初设计不合理，直接将浏览量作为一个字段，用户每点击一次的时候就异步更新浏览量，但是此处没有更新缓存，如果手动更新缓存的话，基本上每点击一次都得执行更新操作，同样也不合理。所以，目前本站，你们在页面上看到的浏览量和数据库中的浏览量并不是一致的。有兴趣的可以点击[我的网站](http://www.wenzhihuai.com)玩玩~~


# 五、题外话
兄弟姐妹们啊，个人网站只是个小项目，纯属为了学习而用的，文章可以看看，但是，就不要抓取了吧。。。。一个小时抓取6万次宝宝心脏真的受不了，虽然服务器一切都还稳定==
<div align="center">![](http://image.wenzhihuai.com/images/20180119044345.png)</div>


**个人网站**：[http://www.wenzhihuai.com](http://www.wenzhihuai.com)
**个人网站源码，希望能给个star**：[https://github.com/Zephery/newblog](https://github.com/Zephery/newblog)



参考：
1.[《深入理解mybatis原理》 MyBatis的一级缓存实现详解](http://blog.csdn.net/luanlouis/article/details/41390801)
2.[《深入理解mybatis原理》 MyBatis的二级缓存的设计原理](http://blog.csdn.net/luanlouis/article/details/41408341)
3.[聊聊Mybatis缓存机制](https://mp.weixin.qq.com/s/Ju4d71VrL0omGkV3s3U_1Q)
4.[Spring思维导图](https://www.jianshu.com/p/0b00cbba40f3)
5.[SpringMVC + MyBatis + Mysql + Redis(作为二级缓存) 配置](http://blog.csdn.net/xiadi934/article/details/50786293)
6.《深入分布式缓存：从原理到实践》  