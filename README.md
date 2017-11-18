# 个人网站的建立  
各位大佬瞄一眼[我的个人网站](http://www.wenzhihuai.com/)呗 。如果觉得不错，希望能在GitHub上麻烦给个star，GitHub地址[https://github.com/Zephery/newblog](https://github.com/Zephery/newblog) 。
大学的时候萌生的一个想法，就是建立一个个人网站，前前后后全部推翻重构了4、5遍，现在终于能看了，下面是目前的首页。
<div align="center">

![](http://image.wenzhihuai.com/home.png?imageView2/2/w/600)

</div>

由原本的ssh变成ssm，再变成ssm+shiro+lucene，到现在的前后台分离。前台使用ssm+lucene，后台使用spring boot+shiro。其中，使用pagehelper作为分页，lucene用来搜索和自动补全，使用百度统计的API做了个日志系统，统计pv和uv什么的，同时，还有使用了JMX来观察JVM的使用和cpu的使用率，机器学习方面，使用了adaboost和朴素贝叶斯对微博进行分类，有兴趣的可以点点[有点意思](http://www.wenzhihuai.com/weibonlp.html)这个页面。
本文从下面这几个方面来讲讲网站的建立：<br/>
1. [建站故事与网站架构](https://github.com/Zephery/newblog/blob/master/doc/1.%E5%8E%86%E5%8F%B2%E4%B8%8E%E6%9E%B6%E6%9E%84.md)<br/>
2. [lucene搜索的使用](https://github.com/Zephery/newblog/blob/master/doc/2.Lucene%E7%9A%84%E4%BD%BF%E7%94%A8.md)<br/>
3. [使用quartz来定时备份数据库](https://github.com/Zephery/newblog/blob/master/doc/3.%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1.md)<br/>
4. [使用百度统计api做日志系统](https://github.com/Zephery/baidutongji/blob/master/README.md)<br/>
5. [Nginx小集群的搭建](https://github.com/Zephery/newblog/blob/master/doc/6.%E5%B0%8F%E9%9B%86%E7%BE%A4%E9%83%A8%E7%BD%B2.md)<br/>
6. [数据库]<br/>
7. 使用机器学习对微博进行分析<br/>
8. 网站性能优化<br/>
9. SEO优化<br/>

## 1.建站故事与网站架构
### 1.1建站过程
起初，是因为学习的时候老是找不到什么好玩而又有挑战性的项目，看着struts、spring、hibernate的书，全都是一些小项目，做了感觉也没啥意义，有时候在博客园看到别人还有自己的网站，特羡慕，最终就选择了自己做一个个人网站。期初刚学的ssh，于是开始了自己的ssh搭建个人网站的行程，但是对于一个后端的人来说，前端是个大问题啊。。。。所以初版的时候差不多全是纯生的html、css、js，至少发博客这个功能实现了，但是确实没法看。前前后后折腾了一个多月，决定推翻重做，到百度看看别人怎么做的。首先看到的是[杨青](http://www.yangqq.com/)的网站，已经好几年没更新了，前端的代码看起来比较简单，也是自己能够掌握的，但是不够美观，继续找，在模板之家发现了一个高大上的模板。
<div align="center">

![](http://image.wenzhihuai.com/joihfiohewifoheifahiauhvuia.png?imageView2/2/w/500)

</div>
第二版的界面确实是这样的，只是把图片的切换变成了wowslider，也是简单的用bootstrap和pagehelper做了下分页，现在的最终版保留了它的header，然后评论框使用了多说（超级怀念多说）。后端也由原来的ssh变成了ssm，之后加上了lucene来对文章进行索引。之后，随着多说要关闭了，突然之间有很多div都不适应了（我写死了的。。。），再一次，没法看，不想看，一怒之下再次推翻重做，变成了现在这个版本。  

最终版本在考虑时，也找了很多模板，影响深刻的是[tale](https://tale.biezhi.me)和[欲思](https://yusi123.com)这两个主题，期中，tale使用的是java语言写的，刚知道的那一刻我就没好感了，java后端我是要自己全部写的，tale这个页面简洁但是不够炫，而且内容量太低，可能就只是个纯博客，之后发现了欲思，拓展性强，只可惜没有静态的版本，后台是纯生的PHP（嗯，PHP是世界上最好的语言），看了看，没事，保存网页，前端自己改，后端自己全部重写，最终变成了现在这个版本，虽然拼接的时候各种css、js混入。。。。还好在做网站性能优化的时候差不多全部去掉了。最终版加入redis、quartz、shiro等，还有python机器学习、flask的restful api，可谓是大杂烩了。
页面看着还算凑合，至少不是那种看都看不过去的那种了，但是仔细看看，还是有不少问题的，比如瀑布流，还有排版什么的。只能等自己什么时候想认真学学前端的东西了。
已经部署在腾讯云服务器上，存储使用了七牛云的cdn。

### 1.2 网站整体技术架构
最终版的技术架构图如下：
<div align="center">

![](http://image.wenzhihuai.com/awfawefwefwef.png)

</div>

网站核心主要采用Spring SpringMVC和Mybatis，下图是当访问一篇博客的时候的运行流程，参考了[张开涛](http://jinnianshilongnian.iteye.com/blog/1594806)的博客。
<div align="center">

![](http://image.wenzhihuai.com/awefaweagregrgbwerbwer.png)

</div>

**运行流程分析**<br/>
1. 浏览器发送http请求。/blogdetail.html?blogid=1。<br/>
2. tomcat容器初始化，顺序为context-param>listener>filter>servlet，此时，spring中的bean还没有被注入的，不建议在此处加载bean，网站声明了两个类（IPFilter和CacheControlFilter），IPFilter用来拦截IP，CacheControlFilter用来缓存。<br/>
3. 初始化Spring。<br/>
4. DispatcherServlet——>HandlerMapping进行请求到处理的映射，HandlerMapping将“/blogdetail”路径直接映射到名字为“/blogdetail”的Bean进行处理，即BlogController。<br/>
5. 自定义拦截器，其中BaseIntercepter实现了HandleInterceptor的接口，用来记录每次访问的链接以及后台响应的时间。<br/>
6. DispatcherServlet——> SimpleControllerHandlerAdapter，SimpleControllerHandlerAdapter将HandlerExecutionChain中的处理器适配为BlogController。<br/>
7. BlogController执行查询，取得结果集返回数据。<br/>
8. blogdetail（ModelAndView的逻辑视图名）——>InternalResourceViewResolver， InternalResourceViewResolver使用JstlView，具体视图页面在/blogdetail.jsp。<br/>
9. JstlView（/blogdetail.jsp）——>渲染，将在处理器传入的模型数据(blog=Blog！)在视图中展示出来。<br/>
10. 返回响应。<br/>

### 1.3 日志系统
日志系统架构如下：
<div align="center">

![](http://image.wenzhihuai.com/awfawefwefawefwef.png)

</div>

[日志系统](http://www.wenzhihuai.com/log.html)曾经尝试采用过ELK，实时监控实在是让人不能不称赞，本地也跑起来了，但是一到服务器，卡卡卡，毕竟（1Ghz CPU、1G内存），只能放弃ELK，采用百度统计。百度统计提供了Tongji API供开发者使用，只是有次数限制，2000/日，实时的话实在有点低，只能统计前几天的PV、UV等开放出来。其实这个存放在mysql也行，不过这些零碎的数据还是放在redis中，方便管理。
出了日志系统，自己对服务器的一些使用率也是挺关心的，毕竟服务器配置太低，于是利用了使用了tomcat的JMX来对CPU和jvm使用情况进行监控，这两个都是实时的。出了这两个，对内存的分配做了监控，Eden、Survivor、Tenured的使用情况。<br/>

### 1.4 【有点意思】自然语言处理  
本人大学里的毕业设计就是基于AdaBoost算法的情感分类，学到的东西还是要经常拿出来看看，要不然真的浪费了我这么久努力做的毕业设计啊。构建了一个基本的情感分类小系统，每天抓取微博进行分类存储在MySql上，并使用flask提供Restful API给java调用，可以点击[这里](http://www.wenzhihuai.com/weibonlp.html)尝试（请忽略Google的图片）。目前分类效果不是很明显，准确率大概只有百分之70%，因为训练样本只有500条（找不到训练样本），机器学习真的太依赖样本的标注。这个，只能请教各位路人大神指导指导了。
<div align="center">

![](http://image.wenzhihuai.com/QQ%E6%88%AA%E5%9B%BE20170825141127.png)

</div>

## 总结<br/>
断断续续，也总算做了一个能拿得出手仍要遮遮掩掩才能给别人看的网站，哈哈哈哈哈，也劳烦各位帮忙找找bug。前前后后从初学Java EE就一直设想的事也还算有了个了结，以后要多多看书，写点精品文章。PS：[GitHub上求给个Star](https://github.com/Zephery/newblog)，以后面试能讲讲这个网站。
