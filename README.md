微信捐赠:


<div align="center">

![](http://image.wenzhihuai.com/images/202004271142001592625207.png?imageView2/2/w/300)

</div>

# 个人博客技术选型

[![GitHub stars](https://img.shields.io/github/stars/Zephery/newblog.svg)](https://github.com/Zephery/newblog/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/Zephery/newblog.svg)](https://github.com/Zephery/newblog/network)
[![GitHub issues](https://img.shields.io/github/issues/Zephery/newblog.svg)](https://github.com/Zephery/newblog/issues)
[![Twitter](https://img.shields.io/twitter/url/https/github.com/Zephery/newblog.svg?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2FZephery%2Fnewblog)

**网站站点**：[http://www.wenzhihuai.com/](http://www.wenzhihuai.com/)

**Java后端框架**：Spring、Spring MVC、Mybatis、WebSocket（实时推送）、Lucene（搜索系统）、JMX  
**前端框架**：Bootstrap、Jquery、Highcharts、Echarts、WaterFall（瀑布流）、WowSlider（图片切换）  
**分布式相关**：Redisson(分布式锁)、dubbo  
**缓存**：Redis（日志系统等）  
**数据库**：MySQL  
**部署**：Tomcat、Nginx、阿里云服务器、七牛云CDN  
**Python相关**：百度统计的获取、Flask提供文本分析API  
**其他**：MongoDB（目前只用来记录数据库启动）、RabbitMQ（目前只用来记录请求）、畅言

### 注意

**本网站只是前台，还有个[博客管理系统](https://github.com/Zephery/newblogback)，由于是个人使用，没怎么整，有兴趣可以看看**

**此项目涉及到的依赖（例如：百度统计账号、文本分析API等）实在太多，不能直接copy。自己折腾吧，加油，建站（特别是自己的网站）是个锻炼自己的好机会。如果有疑问，可以联系我哦**

**BTW，如果可以，希望给个star或者fork奖励**

### 相关博客文章：<br/>

1. [建站故事与网站架构](https://github.com/Zephery/newblog/blob/master/doc/1.%E5%8E%86%E5%8F%B2%E4%B8%8E%E6%9E%B6%E6%9E%84.md)<br/>
2. [lucene搜索的使用](https://github.com/Zephery/newblog/blob/master/doc/2.Lucene%E7%9A%84%E4%BD%BF%E7%94%A8.md)<br/>
3. [使用quartz来定时备份数据库](https://github.com/Zephery/newblog/blob/master/doc/3.%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1.md)<br/>
4. [使用百度统计api做日志系统](https://github.com/Zephery/baidutongji/blob/master/README.md)<br/>
5. [Nginx小集群的搭建](https://github.com/Zephery/newblog/blob/master/doc/6.%E5%B0%8F%E9%9B%86%E7%BE%A4%E9%83%A8%E7%BD%B2.md)<br/>
6. [数据库备份](https://github.com/Zephery/newblog/blob/master/doc/7.%E6%95%B0%E6%8D%AE%E5%BA%93%E5%A4%87%E4%BB%BD.md)<br/>
7. [那些牛逼的插件]()
8. 使用机器学习对微博进行分析<br/>
9. 网站性能优化<br/>
10. SEO优化<br/>

### 网站首页

首页耗了很多时间在里面，包括加载速度，毕竟影响用户的体验。一直都想做的炫酷一点，不过代价是速度越来越慢，光是初次加载到看到页面就需要7秒，加载完需要30秒。这种速度实在不能忍，直到现在全部换成了CDN，去掉了不能访问的链接（谷歌服务）等，不能说特别快，但是至少不会让用户等7秒了。
<div align="center">

![](http://image.wenzhihuai.com/home.png?imageView2/2/w/600)

</div>

### 技术架构图如下(已过时，有空再更新)

一直想在选型上采用点新技术，有些用上确实是完美，但是有些却不知用来干什么好，比如RabbitMQ和MongoDB，毕竟个人网站没有这些业务，有网友说没必要用，毕竟没必要为了业务而业务，等以后有需求了再优化这些技术在博客中的嵌入吧。↖(
^ω^)↗
<div align="center">

![](http://image.wenzhihuai.com/awfawefwefwef.png)

</div>

### 缓存架构如下

起先访问速度实在是太慢了，即使开启了Mybatis的一二级缓存，后来慢慢的加入Ehcache，速度好多了，不过为了模仿大型网站中的多级缓存的概念，还是继续引入Redis作为二级缓存，很好很开心，哈哈哈，不过偶尔网速不好，服务器有点卡的时候，首页的访问还是不能达到满意，后来看开涛的《亿级流量》，感觉把首页每个一段时间抓取，存成静态页面，写到redis，然后每次访问的时候，直接在nginx使用lua访问redis读取然后返回给客户端，奇思妙想还真成了。
<div align="center">

![](https://upyuncdn.wenzhihuai.com/201803170219421253122648.png)

</div>

### 日志系统架构如下

[日志系统](http://www.wenzhihuai.com/log.html)曾经尝试采用过ELK，实时监控实在是让人不能不称赞，本地也跑起来了，但是一到服务器，卡卡卡，毕竟（1Ghz
CPU、1G内存），只能放弃ELK，采用百度统计。百度统计提供了Tongji
API供开发者使用，只是有次数限制，2000/日，实时的话实在有点低，只能统计前几天的PV、UV等开放出来。其实这个存放在mysql也行，不过这些零碎的数据还是放在redis中，方便管理。
除了日志系统，自己对服务器的一些使用率也是挺关心的，毕竟服务器配置太低，于是利用了使用了tomcat的JMX来对CPU和jvm使用情况进行监控，这两个都是实时的。出了这两个，对内存的分配做了监控，Eden、Survivor、Tenured的使用情况。
<div align="center">

![](https://upyuncdn.wenzhihuai.com/201803170304371892629314.png)

</div>

### 文本分类

本人大学里的毕业设计就是基于AdaBoost算法的情感分类，学到的东西还是要经常拿出来看看，要不然真的浪费了我这么久努力做的毕业设计啊。构建了一个基本的情感分类小系统，每天抓取微博进行分类存储在MySql上，并使用flask提供Restful
API给java调用，可以点击[这里](http://www.wenzhihuai.com/weibonlp.html)尝试（请忽略Google的图片）。目前分类效果不是很明显，准确率大概只有百分之70%，因为训练样本只有500条（找不到训练样本），机器学习真的太依赖样本的标注。这个，只能请教各位路人大神指导指导了。
<div align="center">

![](http://image.wenzhihuai.com/QQ%E6%88%AA%E5%9B%BE20170825141127.png)

</div>


### 切换至spring boot 3.0
https://stackoverflow.com/questions/4928271/how-to-install-jstl-the-absolute-uri-http-java-sun-com-jstl-core-cannot-be-r

### 更新日志

**2016-3-21**：学完ssh，感觉不扎实，心想要整个自己的博客网站  
**2016-4-10**：前端采用原生的html、css、js，后端使用ssh，增删改查，至少博客能写了。  
**2016-5-11**：后悔了，实在太丑了，要不改用PHP？  
**2016-5-12**：看到了杨青、java1234的博客，嗯，还是继续java吧，即使页面会low一点  
**2016-5-13**：据说ssh落后了，好吧，弃用ssh，改用ssm，继续  
**2016-5-15**：由于页面low爆了，抛弃之前所有前端页面。换模板，整了个旅游的高大上的模板，开始改  
**2016-5-28**：后端根据java1234的Lucene整合进个人网站  
**2016-6-3**：在腾讯云上线，唉，没人访问  
**2016-6-3至6-30**：考试，项目暂停  
**2016-7-1**：Mybatis分页真头疼，到底是采用逻辑上分页还是物理上？  
**2016-7-1**：引入PageHelper分页插件  
**2016-7-1**：后台编辑器换成editor.md，数据库中保存之后的html，没选择保存原生的md  
**2016-7-10**：处理MyBatis一对一，一对多的关系  
**2016-7-15**：修改Lucene，修改前端模板，好像首页图片切换效果不太好哦  
**2016-7-29**：抛弃之前的首页图片切换效果使用WOWSlider，加上多说，6666，好看一点点了  
**2016-8-3**：更新版上下，额，，，还是没人访问。。。。  
**2016-8-3至8-30**：好好建站居然没人访问。。信心受挫，专注并发去，项目暂停。  
**2016-9-1**：整理整理网站，如果面试有问到的话，可以展示展示  
**2016-9-20至2016-12-31**：好好实习，认真工作，项目暂停  
**2017-1-10**：在家无聊，想起自己还有腾讯云服务器，好吧再看看吧。  
**2017-1-15**：editor.md存储，选择数据库中保存原生md和html  
**2017-1-20**：引入Masonry瀑布流，让网站更炫，呵呵，结果感觉变丑了  
**2017-1-24**：瀑布流换成waterfall，无线加载，好像不太对啊  
**2017-2至3月**：准备春招，专注JVM，项目暂停  
**2017-4-3**：多说要关闭了，换成畅言吧  
**2017-4-5**：日志系统建一个吧，ELK还是Kafka+flume？  
**2017-4-8**：本地都跑起来了，但是服务器，1GHz CPU+1GHz的内存卡死了，根本跑不起ELK  
**2017-4-10**：想到百度统计？  
**2017-5至6-15**：准备毕业设计，项目继续暂停  
**2017-6-20**：面试失败，绝望，想起面试的时候说到了自己博客，决定重构项目  
**2017-6-21**：右键欲思，保存网页，布局就使用这个了  
**2017-6-22**：决定前台后台分析，给用户看的用ssm，后台管理系统采用spring boot  
**2017-6-23**：上传GitHub，前台的觉[newblog]()，后台管理系统叫[newblogback]()  
**2017-6-24**：修改布局，标签云那个效果整了好久。后台使用Themyleaf  
**2017-6-25**：引入微博秀、天气控件、social-share等前端插件，顺眼了好多  
**2017-6-26**：引入百度统计，哈哈，我真是天才  
**2017-6-27**：Themyleaf，后台改改改，想着反正只有我一个人用，那就随便点吧  
**2017-6-30**：结合Highcharts，日志系统基本框架完毕  
**2017-7-1**：瀑布流怎么办？整合整合，还是没达到预期的效果  
**2017-7-2**：大学的毕业设计怎么办？文本分类还是蛮有意思的，好，结合进去  
**2017-7-3**：使用Flask提供一个RESTFUL API，能够给Java调用  
**2017-7-4至7月20**：继续优化优化界面  
**2017-7-21**：新域名（[www.wenzhihuai.com](http://www.wenzhihuai.com)）通过，初步上线  
**2017-7-22**：highcharts的地域图好像不太好，用echarts，毕竟国产  
**2017-7-23**：SSO，百度搜我的名字，排名第一了哈哈哈哈  
**2017-7-24**：想起百度，搜索的时候输入一个词就弹出相关词，可以可以，我要整这个  
**2017-7-28**：Lucene Suggest+Autocomplete结合进去，效果还可以  
**2017-8-13**：社交分享抛弃JiaThis，真的low，换成baidu-share.js  
**2017-8-15**：Nginx集群、MySQL集群、Tomcat集群  
**2017-8-24**：数据库连接池换成Druid  
**2017-9-12**：引入RabbitMQ，额，好像个人网站没这个需求，用来记录访问的吧  
**2017-10-14**：引入MongoDB，额，这个也没需求啊，用来记录数据库的连接情况吧  
**2017-10-27**：分布式下Tomcat Session好像不一致，换成Spring-Session  
**2017-11-14**：网友建议ajax轮询不好，最好用WebSocket，OK，一天之内整完  
**2017-12-4**：服务器被黑，好吧，换成JDK9.0，加油↖(^ω^)↗  
**2018-1-3**：增加周报页面，引入dubbo，更新kafka    
**2018-1-13**：增加切面，对service.impl下的所有方法监测耗时  
**2018-1-18**：唉，终于换成了Spring-Redis作为缓存    
**2018-2-9**：感觉缓存还是不够快，nginx中加上openresty    
**2018-3-4**：加入Redisson分布式锁，减去使用ip来判断的麻烦      
**2018-3-20**：七牛云流量不够，加上又拍云吧      
**2018-4-29**：logback打到kafka里面去        
**2020-2-23**：由spring改为springboot        
**2021-1-1**：去掉了一些脏代码，只做博客，迁回腾讯云重新上线        
**2021-2-28**：优化了日志模块，增加了jprofiler        
**2021-7-17**：迁移至tke，并改成war包运行  
**2022-4-2**：升级spring boot版本，切换成mybatis plus，切换cdn  
**2023-3-26**：升级spring boot 3，考虑要不要改掉jsp

#### 持续更新...
