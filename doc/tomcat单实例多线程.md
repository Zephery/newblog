最近，一直使用单例模式来写一些工具类，之前一直没有思考单例模式在tomcat下的运行效果，直到要做一个单点登录系统使用Apache HttpClient来调用qq、微信的接口，才慢慢觉得不对劲。
单例模式最初的定义出现于《设计模式》（艾迪生维斯理, 1994）：“保证一个类仅有一个实例，并提供一个访问它的全局访问点。”
Java中单例模式定义：“一个类有且仅有一个实例，并且自行实例化向整个系统提供。”
我们再控制层中添加一个访问测试。
```java
    @RequestMapping("/singleToMany")
    @ResponseBody
    public String singleToMany() {
        SingleToMany.getInstance().test();
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
```

实验一：单例模式中声明一个实例，并使用两个浏览器进行访问
```java
public class SingleToMany {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(SingleToMany.class);
    private static SingleToMany instance = null;
    private String name;

    public SingleToMany() {
    }

    public SingleToMany(String name) {
        this.name = name;
    }

    public synchronized static SingleToMany getInstance() {
        if (instance == null) {
            instance = new SingleToMany(DateTime.now().toString("HH:mm:ss SSS"));
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
        }
        return instance;
    }

    public void test() {
    }
}
```
我们用两个浏览器，接近同一时间来访问，实验结果：
```html
14:21:53 790
com.myblog.util.SingleToMany@53ec588d
14:21:53 790
com.myblog.util.SingleToMany@53ec588d
```


实验二：test方法中添加对实例重新声明
```java
    public void test() {
        System.out.println(SingleToMany.instance.name);
        System.out.println(instance.toString());
        synchronized (this) {
            instance = new SingleToMany(DateTime.now().toString("HH:mm:ss SSS"));
        }
    }
```
同样，我们用两个浏览器接近同一时间访问
```html
14:21:33 790
com.myblog.util.SingleToMany@678cfb9d
14:21:43 790
com.myblog.util.SingleToMany@1920784a
```


实验三：不重新声明instance，使用不同类来调用SingleToMany。上面两个实验都是在IndexController里面的，我们在另一个Controller里面写一个名字不同方法相同的。
```java
@Controller
public class BoardController {
    @RequestMapping("/singleToMany2")
    @ResponseBody
    public String singleToMany2() {
        SingleToMany.getInstance().test();
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
```
实验结果：
同样，我们用两个浏览器接近同一时间访问
```html
15:03:37 900
com.myblog.util.SingleToMany@375f5c56
15:03:37 900
com.myblog.util.SingleToMany@375f5c56
```


由上面两个实验，可以知道，单例模式中，实例是所有线程共享的，并且由于声明的时候是static的变量，当一个类调用SingleToMany这个类时，获取到SingleToMany的实例是相同的。如果换成类加载不同，得到的单例也是不同的，具体的查看[类加载器与单例](https://yq.aliyun.com/articles/38927)，不做实验了。

至此，还是比较好奇，Tomcat是如何实现一个webapps单实例多线程的。



参考:  
[类加载器与单例](https://yq.aliyun.com/articles/38927)
