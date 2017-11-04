<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/18
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<footer class="footer">
    <div class="footer-inner">
        <div class="copyright pull-left">
            <a href="${pageContext.request.contextPath}" title="Zephery">Zephery</a> 版权所有丨采用<a
                href="http://yusi123.com/"> 欲思 </a>主题丨基于 Spring SpringMVC Mybatis 构建 © 2016-
            <script>
                var date = new Date();  //创建对象
                var y = date.getFullYear();     //获取年份
                document.write(y);
            </script>
            丨托管于
            <a rel="nofollow" target="_blank" href="https://www.aliyun.com">阿里云&七牛云</a>
            |
            <a rel="nofollow" target="_blank" href="http://www.wenzhihuai.com:8081/admin/login.html">后台管理 </a>
            丨
            <a rel="nofollow" target="_blank"
               href="http://www.miitbeian.gov.cn">粤ICP备17092242号-1</a>
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
