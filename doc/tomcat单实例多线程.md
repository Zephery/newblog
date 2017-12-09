最近，一直使用单例模式来写一些工具类，之前一直没有思考单例模式在tomcat下的运行效果，直到要做一个单点登录系统使用Apache HttpClient来调用qq、微信的接口，才慢慢觉得不对劲。
单例模式最初的定义出现于《设计模式》（艾迪生维斯理, 1994）：“保证一个类仅有一个实例，并提供一个访问它的全局访问点。”
Java中单例模式定义：“一个类有且仅有一个实例，并且自行实例化向整个系统提供。”
```java
    @RequestMapping("/singleToMany")
    @ResponseBody
    public String singleToMany() {
        SingleToMany.getInstance().test();
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
```

```java

```