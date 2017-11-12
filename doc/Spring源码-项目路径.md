# Spring源码——WebUtils
个人网站中部署的服务器共有两台，由于是分布式的环境，为了防止有效的针对具体某个服务器出现的问题，需要在网页上加上服务器的IP地址和项目启动的时间，但是由于资源的问题，最好还是不要使用ajax，毕竟每次都要请求的一次的话实在是太消耗资源了，这里，我采用的方式是在项目启动的时候，就获取服务器公网的IP，然后替换掉jsp的文件，应该是目前最好的方式了。捣鼓的半天，做成的效果如下：
<div align="center">

![](http://image.wenzhihuai.com/images/20171112050154.png)

</div>

过程主要如下：
1. 获取公网IP
2. 添加自定义监听器

# 1.获取公网IP
理论上来说，通过NAT连接的服务器，特别是阿里云的VPS这类的，仅仅通过java来查看本地IP地址只能获取到自己的私网地址，而对于公网IP来说，是不能获取的到的。所以，我们必须使用另一种方式。在Linux中，我们会使用curl icanhazip.com这类命令来获取自己的公网IP，相当与发送了个请求，而对于Java来说，获取方式也是类似。即对外发送一个请求，让外面的服务器判断自己服务器的公网IP即可，原理也就这样，代码使用了对HttpClient封装过后的HttpHelper，很简单。
```java
        String ip = "";
        String url = "http://icanhazip.com/";
        try {
            ip = HttpHelper.getInstance().get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
```
这样就能获取到公网IP了，除了这个网站，下列网址也能获取到公网IP：
（1）http://icanhazip.com/
（2）http://ip.chinaz.com
# 2.添加自定义监听器
获取到IP了，下一步是让它写死在jsp中，我们可以通过自定义监听器，当项目启动的时候加载该监听器，在监听器里对foot.jsp进行操作。首先是要获取项目的路径，网上参考了[Java EE获取路径全攻略](https://www.cnblogs.com/java-class/p/5227423.html)之类的，其实用servletContextEvent.getServletContext().getRealPath("/")也行，Spring中WebAppRootListener原理也跟这个类似的。
首先，在web.xml中添加：
```xml
    <context-param>
        <param-name>webAppRootKey</param-name>
        <!--名字可以自定义-->
        <param-value>myblog.path</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.util.WebAppRootListener</listener-class>
    </listener>
```
然后在java代码中获取方式：
```java
//获取路径
String user = System.getProperty("myblog.path");
String abPath = WinOrLinux.isWin() ? user + "foot.jsp" : user + "foot.jsp";
//获取IP
String ip = IPUtils.getServerIp();
//读取然后写入，可以使用Jsoup但是处理比较麻烦，简单点，在网页中写某个字段，然后replace一下即可
File file = new File(abPath);
BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(abPath), "UTF-8"));//构造一个BufferedReader类来读取文件
String s = null;
StringBuilder result = new StringBuilder();
while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
    result.append(s);
    result.append("\r\n");
}
br.close();
//替换
String newStr = result.toString().replaceAll("serverIp", ip).replaceAll("projectStartTime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
FileOutputStream fileOutputStream = new FileOutputStream(file);
OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
outputWriter.write(newStr);
```
然后Spring在启动的时候都会更新一下当前的IP地址和项目启动的时间。


# 3.WebUtils源码

```java
public abstract class WebUtils {
    ...
    //web.xml中的定义
    public static final String WEB_APP_ROOT_KEY_PARAM = "webAppRootKey";
    
    public static void setWebAppRootSystemProperty(ServletContext servletContext) throws IllegalStateException {
        Assert.notNull(servletContext, "ServletContext must not be null");
        //获取项目的路径
        String root = servletContext.getRealPath("/");
        if (root == null) {
            throw new IllegalStateException("Cannot set web app root system property when WAR file is not expanded");
        } else {
            //查看web.xml中是否配置了webAppRootKey
            String param = servletContext.getInitParameter("webAppRootKey");
            String key = param != null ? param : "webapp.root";
            String oldValue = System.getProperty(key);
            //如何符合，则设置值
            if (oldValue != null && !StringUtils.pathEquals(oldValue, root)) {
                throw new IllegalStateException("Web app root system property already set to different value: '" + key + "' = [" + oldValue + "] instead of [" + root + "] - Choose unique values for the 'webAppRootKey' context-param in your web.xml files!");
            } else {
                System.setProperty(key, root);
                servletContext.log("Set web app root system property: '" + key + "' = [" + root + "]");
            }
        }
    }
    ...
}
```







参考：  
[Java EE获取路径全攻略](https://www.cnblogs.com/java-class/p/5227423.html)
