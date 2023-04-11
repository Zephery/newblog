<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/18
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<footer class="footer">
    <div class="footer-inner">
        <div style="float: left;margin-top: 0;width: 100px;height: 50px">
            <a href="https://www.ej-technologies.com/products/jprofiler/overview.html">
                Java profiler
                <img src="http://image.wenzhihuai.com/jprofiler.png" style="float: left;width: auto"/>
            </a>
        </div>
        <div style="float: right;margin-top: 0;width: 100px;height: 50px">
            <a href="https://www.upyun.com/?utm_source=lianmeng&utm_medium=referral">
                <img src="http://image.wenzhihuai.com/youpailogo6.png" style="float: left;width: auto"/>
            </a>
        </div>
        <div class="copyright pull-left" style="float: left">
            <a href="${pageContext.request.contextPath}" title="Zephery">Zephery</a> 版权所有丨改自<a
                href="http://yusi123.com/"> 欲思 </a>主题丨基于 SSM 构建 © 2016-
            <script>
                var date = new Date();  //创建对象
                var y = date.getFullYear();     //获取年份
                document.write(y);
            </script>
            丨托管于
            <a rel="nofollow">云存储</a>
            |
            <a rel="nofollow" target="_blank" href="http://www.wenzhihuai.com:8081/admin/index.html">后台管理 </a>
            丨
            <a rel="nofollow" target="_blank"
               href="https://beian.miit.gov.cn/">粤ICP备17092242号</a>
        </div>
        <%
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = df.format(d);
            String ip = "127.0.0.1";
        %>
        <div class="copyright pull-left" style="float: left;">
            本服务器IP地址：<%=ip%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 项目启动时间：<%=now %>
        </div>
        <div>
            <!-- cnzz stats -->
            <script type="text/javascript">
                var cnzz_s_tag = document.createElement('script');
                cnzz_s_tag.type = 'text/javascript';
                cnzz_s_tag.async = true;
                cnzz_s_tag.charset = 'utf-8';
                cnzz_s_tag.src = 'https://w.cnzz.com/c.php?id=1262457277&async=1';
                var root_s = document.getElementsByTagName('script')[0];
                root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
            </script>
        </div>
    </div>
</footer>
