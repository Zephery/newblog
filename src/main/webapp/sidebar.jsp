<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/21
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<link href="http://image.wenzhihuai.com/newlypublished.css?ver=20171017" rel="stylesheet">
<script>
    $.ajax({
        url: "${pageContext.request.contextPath}/blogbyhits.html",
        type: "get",
        header: {"Content-type":"application/json"},
        dataType: 'json',
        success: function (data) {
            var html = "";
            var guessyourlike = $(".guessyourlike");
            for (var i = 0; i < data.length; i++) {
                html += "    <li><a href=\"" + "${pageContext.request.contextPath}/getblogdetail.html?blogid=" + data[i].blogid + "\"\n" +
                    "    title=\"" + data[i].title + "\">\n" +
                    "        <span class=\"thumbnail\"><img src=\"" + data[i].imageurl + "\" alt=\"" + data[i].title + "\" width=\"100px\"></span>\n" +
                    "        <span class=\"text\">" + data[i].title + "</span><span class=\"muted\">" + data[i].createAt + "</span>\n" +
                    "    <span class=\"muted\">" + data[i].hits + "浏览</span></a></li>\n";
            }
            guessyourlike.append(html);
        },
        error: function (e) {
        }
    });
    $.ajax({
        url: "${pageContext.request.contextPath}/getjsonbycategories.html",
        type: "get",
        dataType: 'json',
        success: function (data) {
            var html = "";
            var fenlei = $("#fenlei");
            for (var i = 0; i < data.length; i++) {
                html += "<a href=\"${pageContext.request.contextPath}/tech.html?categoryid=" + data[i].cId + "\">\n" +
                    data[i].cName + "</a>\n"
            }
            fenlei.append(html);
        },
        error: function (e) {
        }
    });
    //    <a href="http://www.ibloger.net/tags-12.html" class="tag-link-12" title="214个话题"
    //    style="font-size: 13px;"> Java </a>

    $.ajax({
        url: "${pageContext.request.contextPath}/biaoqianyun.html",
        type: "get",
        dataType: 'json',
        success: function (data) {
            var html = "";
            var fenlei = $(".tags-box");
            for (var i = 0; i < data.length; i++) {
                html += "<a href=\"${pageContext.request.contextPath}/tech.html?tid=" + data[i].key + "\">\n" +
                    data[i].value + "</a>\n"
            }
            fenlei.append(html);
        },
        error: function (e) {
        }
    });

    //link
    $.ajax({
        url: "${pageContext.request.contextPath}/links.html",
        type: "get",
        dataType: 'json',
        success: function (data) {
            var html = "";
            var fenlei = $(".blogroll");
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=" + data[i].url + ">" +
                    data[i].name + "</a></li>\n"
            }
            fenlei.append(html);
        },
        error: function (e) {
        }
    });
    $(document).ready(function () {
        setTimeout(function () {
            var navOffset = $(".widgetRoller").offset().top;
            $(window).scroll(function () {
                var scrollPos = $(window).scrollTop();
                if (scrollPos >= navOffset) {
                    $(".widgetRoller").css({position: "fixed", top: 0, zIndex: 0, width: 330}).fadeIn(100);
                } else {
                    $(".widgetRoller").removeAttr("style");
                }
            });
        }, 2000);
    });

    function test() {
        var div1 = $("#biaoqian");
        if (div1.style.display == "block") {
            div1.style.display = "none";
        } else {
            div1.style.display = "block";
        }
    }
</script>
<aside class="sidebar">
    <div class="widget widget_text" style="height: 115px;margin-bottom: 0px">
        <iframe src="http://www.seniverse.com/weather/weather.aspx?uid=U35799536E&cid=CHBJ000000&l=&p=SMART&a=1&u=C&s=13&m=
        0&x=1&d=0&fc=&bgc=2E93D9&bc=&ti=0&in=1&li=" frameborder="0" scrolling="no" width="330" height="115"
                allowTransparency="true"></iframe>
    </div>

    <div class="widget d_tag">
        <div class="title"><h2>分类</h2></div>
        <div class="d_tags" id="fenlei" style="height: auto">
            <a href="${pageContext.request.contextPath}/life.html">生活笔记</a>
        </div>
    </div>


    <div class="widget d_postlist">
        <div class="title"><h2>猜你喜欢</h2></div>
        <ul class="guessyourlike">
        </ul>
    </div>


<%--    <div class="widget widget_text">--%>
<%--        <div class="title"><h2>新浪微博</h2></div>--%>
<%--        <div class="textwidget">--%>
<%--            <iframe width="100%" height="550" class="share_self" frameborder="0" scrolling="no"--%>
<%--                    src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=0&noborder=0&isWeibo=1&isFans=1&uid=1925306000&verifier=5d4515d6&dpc=1"></iframe>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="widget widget_links">
        <div class="title"><h2>友情链接</h2></div>
        <ul class="xoxo blogroll" style="width: 332px;">
            <li><a href="http://www.99banzou.com" target="_blank">99伴奏网</a></li>
            <li><a href="http://www.anotherhome.net" target="_blank">Anotherhome</a></li>
            <li><a href="http://zhouchenwen.com" rel="acquaintance" target="_blank">ColdCoder</a></li>
            <li><a href="http://www.spotty.com.cn/" target="_blank">DevNews</a></li>
            <li><a href="http://www.findspace.name" target="_blank">Findspace</a></li>
            <li><a href="http://blog.hickwu.com/" target="_blank">Hick</a></li>
        </ul>
    </div>


    <div class="widgetRoller">
        <div class="widget widget_divTags" id="divTags">
            <div class="title"><h2>标签云</h2></div>
            <ul class="widget_divTags_inner">
                <div class="tags-box">
                </div>
            </ul>
        </div>
    </div>
</aside>