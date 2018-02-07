package com.myblog.common;

public class Common {
    public final static String COMMON_JSON = "{\n" +
            " \"is_robot\": false,\n" +
            " \"cmts\": [],\n" +
            " \"good\": false,\n" +
            " \"common\": false,\n" +
            " \"album_wait_audit\": false,\n" +
            " \"price\": 0,\n" +
            " \"rid\": 95800538,\n" +
            " \"buylnk\": \"\",\n" +
            " \"sender_wait_audit\": false,\n" +
            " \"zanc\": 0,\n" +
            " \"sta\": 0,\n" +
            " \"coupon_price\": 0,\n" +
            " \"albnm\": \"花花女子与花花世界\",\n" +
            " \"albid\": 5542595,\n" +
            " \"favc\": 99,\n" +
            " \"wait_audit\": false,\n" +
            " \"ruid\": 802123,\n" +
            " \"id\": 97290923,\n" +
            " \"repc\": 0\n" +
            " }";
    public final static String BAIDU_APP_ID = "10530811";

    public final static String BAIDU_APP_KEY = "LwqnDLTHYIdH3H5pOIM5H3wB";

    public final static String BAIDU_SECRET_KEY = "EIa2DECI9udOOK3acNrg3mxsIGcT7nDK";

    public final static String INDEX = "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Zephery | 温志怀的个人博客\n" +
            "    </title>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"http://image.wenzhihuai.com/66.jpg\"/>\n" +
            "    <link href=\"http://image.wenzhihuai.com/blogbootstrap.css?ver=20171019\" type=\"text/css\" rel=\"stylesheet\"\n" +
            "          media=\"all\">\n" +
            "    <link href=\"http://image.wenzhihuai.com/style.css?ver=201711151452\" type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
            "    <link rel=\"stylesheet\" href=\"http://image.wenzhihuai.com/jingmi.css?ver=20171017\"\n" +
            "          type=\"text/css\" media=\"all\">\n" +
            "    <link href=\"http://image.wenzhihuai.com/lanrenzhijia.css?ver=20171017\" type=\"text/css\"\n" +
            "          rel=\"stylesheet\"/>\n" +
            "    \n" +
            "    <link href=\"https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n" +
            "    <link rel=\"stylesheet\"\n" +
            "          href=\"http://image.wenzhihuai.com/social-share-1.0.2/dist/social-share.min.css?ver=20171017\">\n" +
            "    <script src=\"https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js\"></script>\n" +
            "    <script src=\"https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js\"></script>\n" +
            "    <script src=\"http://image.wenzhihuai.com/social-share-1.0.2/dist/social-share.min.js?ver=20171017\"></script>\n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "\n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    <script type=\"text/javascript\" src=\"http://image.wenzhihuai.com/move-top.js?ver=2017111411\"></script>\n" +
            "    <script type=\"text/javascript\" src=\"http://image.wenzhihuai.com/easing.js?ver=20171017\"></script>\n" +
            "    <script>\n" +
            "        $(function () {\n" +
            "            var el = document.getElementById('share-area');\n" +
            "            var links = [{\n" +
            "                plugin: 'github',\n" +
            "                url: 'https://github.com/Zephery'\n" +
            "            }, {\n" +
            "                plugin: 'weibo',\n" +
            "                url: 'http://weibo.com/1925306000'\n" +
            "            }, {\n" +
            "                plugin: 'facebook',\n" +
            "                args: {\n" +
            "                    id: 'zephery.wen'           // Your facebook ID\n" +
            "                }\n" +
            "            }];\n" +
            "            var options = {\n" +
            "                size: 'sm'\n" +
            "            };\n" +
            "            window.socialShare(el, links, options);\n" +
            "        });\n" +
            "        jQuery(document).ready(function ($) {\n" +
            "            $(\".scroll\").click(function (event) {\n" +
            "                event.preventDefault();\n" +
            "                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);\n" +
            "            });\n" +
            "        });\n" +
            "    </script>\n" +
            "    <!--//end-smoth-scrolling-->\n" +
            "    <!-- Custom Theme files -->\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
            "    <!--SEO-->\n" +
            "    <meta name=\"title\" content=\"Zephery | 温志怀的个人日志\">\n" +
            "    <meta name=\"keywords\" content=\"温志怀,wenzhihuai,java,机器学习,python,日志\"/>\n" +
            "    <meta name=\"description\" content=\"温志怀的个人日志\"/>\n" +
            "    <meta name=\"author\" content=\"温志怀\"/>\n" +
            "    <!--百度统计-->\n" +
            "    <script type=\"text/javascript\">\n" +
            "        var _hmt = _hmt || [];\n" +
            "        (function () {\n" +
            "            var hm = document.createElement(\"script\");\n" +
            "            hm.src = \"https://hm.baidu.com/hm.js?e580b8db831811a4aaf4a8f3e30034dc\";\n" +
            "            var s = document.getElementsByTagName(\"script\")[0];\n" +
            "            s.parentNode.insertBefore(hm, s);\n" +
            "        })();\n" +
            "    </script>\n" +
            "    <!--百度站内搜索验证-->\n" +
            "    <meta name=\"baidu-site-verification\" content=\"me5PEgngG6\"/>\n" +
            "    <!--百度链接提交，自动推送-->\n" +
            "    <script>\n" +
            "        (function () {\n" +
            "            var bp = document.createElement('script');\n" +
            "            var curProtocol = window.location.protocol.split(':')[0];\n" +
            "            if (curProtocol === 'https') {\n" +
            "                bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';\n" +
            "            }\n" +
            "            else {\n" +
            "                bp.src = 'http://push.zhanzhang.baidu.com/push.js';\n" +
            "            }\n" +
            "            var s = document.getElementsByTagName(\"script\")[0];\n" +
            "            s.parentNode.insertBefore(bp, s);\n" +
            "        })();\n" +
            "    </script>\n" +
            "    <!--360搜索-->\n" +
            "    <meta name=\"360-site-verification\" content=\"db160af16a61f74e2657c0540f4ccd0d\"/>\n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    <!--搜狗-->\n" +
            "    <meta name=\"sogou_site_verification\" content=\"Lzg0yrcxwy\"/>\n" +
            "    <!--bing-->\n" +
            "    <meta name=\"msvalidate.01\" content=\"68A11010C9AEE3FD3F5BD421EACC7499\"/>\n" +
            "</head>\n" +
            "<body>\n" +
            "<!--navigation-->\n" +
            "<div class=\"top-nav\">\n" +
            "    <nav class=\"navbar navbar-default\">\n" +
            "        <div class=\"container\">\n" +
            "            <div class=\"navbar-header\">\n" +
            "                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\"\n" +
            "                        data-target=\"#bs-example-navbar-collapse-1\">\n" +
            "                    <span class=\"sr-only\">Toggle navigation</span>\n" +
            "                    <span class=\"icon-bar\"></span>\n" +
            "                    <span class=\"icon-bar\"></span>\n" +
            "                    <span class=\"icon-bar\"></span>\n" +
            "                </button>\n" +
            "            </div>\n" +
            "            <!-- Collect the nav links, forms, and other content for toggling -->\n" +
            "            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
            "                <ul class=\"nav navbar-nav navbar-left\">\n" +
            "                    <li><a href=\"/index.html\"\n" +
            "                           class=\"active\">主页</a></li>\n" +
            "                    <li><a href=\"/tech.html?pagenum=1\"\n" +
            "                           class=\"null\">技术杂谈</a></li>\n" +
            "                    <li><a href=\"/life.html?pagenum=1\"\n" +
            "                           class=\"null\">生活笔记</a></li>\n" +
            "                    <li><a href=\"/trip.html\"\n" +
            "                           class=\"null\">旅行</a></li>\n" +
            "                    <li><a href=\"/log.html\"\n" +
            "                           class=\"null\">日志系统</a></li>\n" +
            "                    <li><a href=\"/board.html\"\n" +
            "                           class=\"null\">留言板</a></li>\n" +
            "                    <li><a href=\"/aboutme.html\"\n" +
            "                           class=\"null\">关于我</a></li>\n" +
            "                    <li><a href=\"/donate.html\"\n" +
            "                           class=\"null\">捐赠</a></li>\n" +
            "                    <li><a href=\"/weibonlp.html\"\n" +
            "                           class=\"null\">有点意思</a></li>\n" +
            "                    <li><a href=\"/open.html\"\n" +
            "                           class=\"null\">开放平台</a></li>\n" +
            "                </ul>\n" +
            "                <div class=\"social-icons\">\n" +
            "                    <div id=\"share-area\" style=\"margin-top: -5px\"></div>\n" +
            "                </div>\n" +
            "                <div class=\"clearfix\"></div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </nav>\n" +
            "</div>\n" +
            "<script type=\"text/javascript\">\n" +
            "    $(document).ready(function () {\n" +
            "        $().UItoTop({easingType: 'easeOutQuart'});\n" +
            "    });\n" +
            "</script>\n" +
            "<a href=\"#\" id=\"toTop\"></a>\n" +
            "<script src=\"https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.js\" type=\"text/javascript\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "    function checkData() {\n" +
            "        var q = $(\"#remote_input\").val();\n" +
            "        if (q == null || q == \"\") {\n" +
            "            alert(\"请输入您要查询的关键字！\");\n" +
            "            return false;\n" +
            "        } else {\n" +
            "            return true;\n" +
            "        }\n" +
            "    }\n" +
            "</script>\n" +
            "<!--header-->\n" +
            "<div class=\"header\">\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"navbar-header\">\n" +
            "            <a class=\"navbar-brand\" href=\"index.html\"><img src=\"http://image.wenzhihuai.com/logo.png?ver=20171017\"\n" +
            "                                                           alt=\"\"></a>\n" +
            "        </div>\n" +
            "        <form action=\"/search.html\" method=\"post\"\n" +
            "              class=\"navbar-form navbar-right\" role=\"search\">\n" +
            "            <div class=\"input-group\" style=\"margin-bottom: -10px\">\n" +
            "                <input type=\"text\" name=\"keyword\"\n" +
            "                       class=\"form-control\" id=\"remote_input\" placeholder=\"Searching...\">\n" +
            "                <span class=\"input-group-btn\">\n" +
            "                    <button id=\"open\" class=\"btn btn-default\" type=\"submit\" onsubmit=\"checkData()\">搜索</button>\n" +
            "\t\t\t</span>\n" +
            "            </div>\n" +
            "        </form>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "<link rel=\"stylesheet\" href=\"http://image.wenzhihuai.com/jquery.autocomplete.css\">\n" +
            "<script src=\"http://image.wenzhihuai.com/jquery.autocomplete.js\" type=\"text/javascript\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "    /******************** remote start **********************/\n" +
            "    var remote_input = $('#remote_input');\n" +
            "    remote_input.autocomplete({\n" +
            "        source: [\n" +
            "            {\n" +
            "                minChars: 1,\n" +
            "                url: \"/ajaxsearch.html?keyword=%QUERY%\",\n" +
            "                type: 'remote'\n" +
            "            }\n" +
            "        ]\n" +
            "    });\n" +
            "    /********************* remote end ********************/\n" +
            "</script>\n" +
            "</html>\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <script src=\"http://image.wenzhihuai.com/responsiveslides.min.js?ver=20171017\"></script>\n" +
            "    <!-- Start WOWSlider.com HEAD section -->\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://image.wenzhihuai.com/wowsliderstyle.css?ver=20171017\"/>\n" +
            "    <!-- End WOWSlider.com HEAD section -->\n" +
            "    <script type=\"text/javascript\">\n" +
            "        $(function () {\n" +
            "            $(\"#slider\").responsiveSlides({\n" +
            "                auto: true,\n" +
            "                nav: true,\n" +
            "                speed: 500,\n" +
            "                namespace: \"callbacks\",\n" +
            "                pager: false\n" +
            "            });\n" +
            "        });\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body class=\"home blog hPC\">\n" +
            "<section class=\"contentcontainer\">\n" +
            "    <div class=\"content-wrap\">\n" +
            "        <div class=\"content\">\n" +
            "            <div>\n" +
            "                <!-- Start WOWSlider.com BODY section -->\n" +
            "                <div id=\"wowslider-container1\">\n" +
            "                    <div class=\"ws_images\">\n" +
            "                        <ul>\n" +
            "                            \n" +
            "                                <li><a href=\"getblogdetail.html?blogid=618\" target=\"_self\">\n" +
            "                                    <img src=\"http://image.wenzhihuai.com/images/20180204060518.png?imageView2/1/w/830/h/500\"\n" +
            "                                         alt=\"Spring AOP小记\" title=\"Spring AOP小记\"\n" +
            "                                         id=\"wows1_0\"/></a>\n" +
            "                                </li>\n" +
            "                            \n" +
            "                                <li><a href=\"getblogdetail.html?blogid=617\" target=\"_self\">\n" +
            "                                    <img src=\"http://image.wenzhihuai.com/images/20180119044345.png?imageView2/1/w/830/h/500\"\n" +
            "                                         alt=\"谈谈个人网站的建立（八）—— 缓存的使用\" title=\"谈谈个人网站的建立（八）—— 缓存的使用\"\n" +
            "                                         id=\"wows1_0\"/></a>\n" +
            "                                </li>\n" +
            "                            \n" +
            "                                <li><a href=\"getblogdetail.html?blogid=615\" target=\"_self\">\n" +
            "                                    <img src=\"http://image.wenzhihuai.com/images/20171104105747.png?imageView2/1/w/830/h/500\"\n" +
            "                                         alt=\" Kafka、Logstash、Nginx日志收集入门\" title=\" Kafka、Logstash、Nginx日志收集入门\"\n" +
            "                                         id=\"wows1_0\"/></a>\n" +
            "                                </li>\n" +
            "                            \n" +
            "                                <li><a href=\"getblogdetail.html?blogid=614\" target=\"_self\">\n" +
            "                                    <img src=\"http://image.wenzhihuai.com/images/20171226065216.png?imageView2/1/w/830/h/500\"\n" +
            "                                         alt=\"TCP/IP、HTTP、HTTPS、HTTP2.0\" title=\"TCP/IP、HTTP、HTTPS、HTTP2.0\"\n" +
            "                                         id=\"wows1_0\"/></a>\n" +
            "                                </li>\n" +
            "                            \n" +
            "                                <li><a href=\"getblogdetail.html?blogid=613\" target=\"_self\">\n" +
            "                                    <img src=\"http://image.wenzhihuai.com/images/20171213120723.png?imageView2/1/w/830/h/500\"\n" +
            "                                         alt=\"12-13\" title=\"12-13\"\n" +
            "                                         id=\"wows1_0\"/></a>\n" +
            "                                </li>\n" +
            "                            \n" +
            "                        </ul>\n" +
            "                    </div>\n" +
            "                    <div class=\"ws_bullets\">\n" +
            "                        <div>\n" +
            "                            \n" +
            "                                <a href=\"/getblogdetail.html?blogid=618\"\n" +
            "                                   title=\"Spring AOP小记\">\n" +
            "                                    <span><img src=\"http://image.wenzhihuai.com/images/20180204060518.png?imageView2/1/w/120/h/90\"\n" +
            "                                               alt=\"Spring AOP小记\"/>Spring AOP小记</span></a>\n" +
            "                            \n" +
            "                                <a href=\"/getblogdetail.html?blogid=617\"\n" +
            "                                   title=\"谈谈个人网站的建立（八）—— 缓存的使用\">\n" +
            "                                    <span><img src=\"http://image.wenzhihuai.com/images/20180119044345.png?imageView2/1/w/120/h/90\"\n" +
            "                                               alt=\"谈谈个人网站的建立（八）—— 缓存的使用\"/>谈谈个人网站的建立（八）—— 缓存的使用</span></a>\n" +
            "                            \n" +
            "                                <a href=\"/getblogdetail.html?blogid=615\"\n" +
            "                                   title=\" Kafka、Logstash、Nginx日志收集入门\">\n" +
            "                                    <span><img src=\"http://image.wenzhihuai.com/images/20171104105747.png?imageView2/1/w/120/h/90\"\n" +
            "                                               alt=\" Kafka、Logstash、Nginx日志收集入门\"/> Kafka、Logstash、Nginx日志收集入门</span></a>\n" +
            "                            \n" +
            "                                <a href=\"/getblogdetail.html?blogid=614\"\n" +
            "                                   title=\"TCP/IP、HTTP、HTTPS、HTTP2.0\">\n" +
            "                                    <span><img src=\"http://image.wenzhihuai.com/images/20171226065216.png?imageView2/1/w/120/h/90\"\n" +
            "                                               alt=\"TCP/IP、HTTP、HTTPS、HTTP2.0\"/>TCP/IP、HTTP、HTTPS、HTTP2.0</span></a>\n" +
            "                            \n" +
            "                                <a href=\"/getblogdetail.html?blogid=613\"\n" +
            "                                   title=\"12-13\">\n" +
            "                                    <span><img src=\"http://image.wenzhihuai.com/images/20171213120723.png?imageView2/1/w/120/h/90\"\n" +
            "                                               alt=\"12-13\"/>12-13</span></a>\n" +
            "                            \n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"ws_shadow\"></div>\n" +
            "                </div>\n" +
            "                <!--wowslider依赖的jquery版本太低，只能继续使用jquery1.11.2-->\n" +
            "                \n" +
            "                <script type=\"text/javascript\"\n" +
            "                        src=\"/js/wowslider/engine1/wowslider.js\"></script>\n" +
            "                <script type=\"text/javascript\"\n" +
            "                        src=\"/js/wowslider/engine1/script.js\"></script>\n" +
            "                <!-- End WOWSlider.com BODY section -->\n" +
            "            </div>\n" +
            "            <div>\n" +
            "                <div class=\"left-ad\"\n" +
            "                     style=\"clear: both;background-color: #fff; width: 30%;float: left;margin-right:2%;\"></div>\n" +
            "                <div class=\"hot-posts\">\n" +
            "                    <h2 class=\"title\">热门排行</h2>\n" +
            "                    <ul>\n" +
            "                        \n" +
            "                        \n" +
            "                            <li>\n" +
            "                                <p><span class=\"muted\">\n" +
            "                            <a data-action=\"ding\" data-id=\"1052\" class=\"action\">\n" +
            "                                <i class=\"fa fa-eye\"></i>\n" +
            "                                <span class=\"count\">427</span> 浏览</a></span>\n" +
            "                                </p><span class=\"label label-1\">1</span>\n" +
            "                                <a href=\"/getblogdetail.html?blogid=615\"\n" +
            "                                   title=\" Kafka、Logstash、Nginx日志收集入门\"> Kafka、Logstash、Nginx日志收集入门</a></li>\n" +
            "                            \n" +
            "                        \n" +
            "                            <li>\n" +
            "                                <p><span class=\"muted\">\n" +
            "                            <a data-action=\"ding\" data-id=\"1052\" class=\"action\">\n" +
            "                                <i class=\"fa fa-eye\"></i>\n" +
            "                                <span class=\"count\">262</span> 浏览</a></span>\n" +
            "                                </p><span class=\"label label-2\">2</span>\n" +
            "                                <a href=\"/getblogdetail.html?blogid=613\"\n" +
            "                                   title=\"12-13\">12-13</a></li>\n" +
            "                            \n" +
            "                        \n" +
            "                            <li>\n" +
            "                                <p><span class=\"muted\">\n" +
            "                            <a data-action=\"ding\" data-id=\"1052\" class=\"action\">\n" +
            "                                <i class=\"fa fa-eye\"></i>\n" +
            "                                <span class=\"count\">232</span> 浏览</a></span>\n" +
            "                                </p><span class=\"label label-3\">3</span>\n" +
            "                                <a href=\"/getblogdetail.html?blogid=617\"\n" +
            "                                   title=\"谈谈个人网站的建立（八）—— 缓存的使用\">谈谈个人网站的建立（八）—— 缓存的使用</a></li>\n" +
            "                            \n" +
            "                        \n" +
            "                            <li>\n" +
            "                                <p><span class=\"muted\">\n" +
            "                            <a data-action=\"ding\" data-id=\"1052\" class=\"action\">\n" +
            "                                <i class=\"fa fa-eye\"></i>\n" +
            "                                <span class=\"count\">168</span> 浏览</a></span>\n" +
            "                                </p><span class=\"label label-4\">4</span>\n" +
            "                                <a href=\"/getblogdetail.html?blogid=614\"\n" +
            "                                   title=\"TCP/IP、HTTP、HTTPS、HTTP2.0\">TCP/IP、HTTP、HTTPS、HTTP2.0</a></li>\n" +
            "                            \n" +
            "                        \n" +
            "                            <li>\n" +
            "                                <p><span class=\"muted\">\n" +
            "                            <a data-action=\"ding\" data-id=\"1052\" class=\"action\">\n" +
            "                                <i class=\"fa fa-eye\"></i>\n" +
            "                                <span class=\"count\">98</span> 浏览</a></span>\n" +
            "                                </p><span class=\"label label-5\">5</span>\n" +
            "                                <a href=\"/getblogdetail.html?blogid=612\"\n" +
            "                                   title=\"单例模式与Tomcat\">单例模式与Tomcat</a></li>\n" +
            "                            \n" +
            "                        \n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=44\">Spring源码<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=618\"\n" +
            "                               title=\"Spring AOP小记\">Spring AOP小记</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=618\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20180204060518.png?imageView2/1/w/200/h/123\" alt=\"Spring AOP小记\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># 一、概述\n" +
            "在通常的开发过程中，我们调用的顺序通常是controller->service-dao，其中，service中包含着太多的业务逻辑，并且还要不断调用dao来实现自身的业务逻辑，经常会导致业务耗时过久，在aop出现之前，方式一...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2018-02-04</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 15浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=618\">\n" +
            "                            <span id=\"sourceId::618\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=617\"\n" +
            "                               title=\"谈谈个人网站的建立（八）—— 缓存的使用\">谈谈个人网站的建立（八）—— 缓存的使用</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=617\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20180119044345.png?imageView2/1/w/200/h/123\" alt=\"谈谈个人网站的建立（八）—— 缓存的使用\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># 一、概述  \n" +
            "## 1.1 缓存介绍\n" +
            "\n" +
            "系统的性能指标一般包括响应时间、延迟时间、吞吐量，并发用户数和资源利用率等。在应用运行过程中，我们有可能在一次数据库会话中，执行多次查询条件完全相同的SQL，MyBatis提供了一级缓存的方...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2018-01-21</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 232浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=617\">\n" +
            "                            <span id=\"sourceId::617\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=615\"\n" +
            "                               title=\" Kafka、Logstash、Nginx日志收集入门\"> Kafka、Logstash、Nginx日志收集入门</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=615\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171104105747.png?imageView2/1/w/200/h/123\" alt=\" Kafka、Logstash、Nginx日志收集入门\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># Kafka、Logstash、Nginx日志收集入门\n" +
            "Nginx作为网站的第一入口，其日志记录了除用户相关的信息之外，还记录了整个网站系统的性能，对其进行性能排查是优化网站性能的一大关键。  \n" +
            "Logstash是一个接收，处理，转发...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2018-01-14</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 427浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=615\">\n" +
            "                            <span id=\"sourceId::615\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=47\">网络<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=614\"\n" +
            "                               title=\"TCP/IP、HTTP、HTTPS、HTTP2.0\">TCP/IP、HTTP、HTTPS、HTTP2.0</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=614\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171226065216.png?imageView2/1/w/200/h/123\" alt=\"TCP/IP、HTTP、HTTPS、HTTP2.0\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">HTTP，全称超文本传输协议（HTTP，HyperText Transfer Protocol)，是一个客户端和服务器端请求和应答的标准（TCP），互联网上应用最为广泛的一种网络协议。客户端是终端用户，服务器端是网站。通过使用Web浏览器、...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-12-24</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 168浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=614\">\n" +
            "                            <span id=\"sourceId::614\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=0\">生活笔记<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=613\"\n" +
            "                               title=\"12-13\">12-13</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=613\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171213120723.png?imageView2/1/w/200/h/123\" alt=\"12-13\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">再一次，加班，累。。。\n" +
            "<div align=\"center\">\n" +
            "\n" +
            "![](http://image.wenzhihuai.com/images/20171213120723.png)\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-12-13</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 262浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=613\">\n" +
            "                            <span id=\"sourceId::613\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=46\">设计模式<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=612\"\n" +
            "                               title=\"单例模式与Tomcat\">单例模式与Tomcat</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=612\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171104105808.png?imageView2/1/w/200/h/123\" alt=\"单例模式与Tomcat\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">最近，一直使用单例模式来写一些工具类，之前一直没有思考单例模式在tomcat下的运行效果，直到要做一个单点登录系统使用Apache HttpClient来调用qq、微信的接口，才慢慢觉得不对劲。\n" +
            "单例模式最初的定义出现于《设计模式》（艾迪...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-12-10</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 98浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=612\">\n" +
            "                            <span id=\"sourceId::612\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=609\"\n" +
            "                               title=\"谈谈个人网站的建立（七）—— 那些建站必备的插件\">谈谈个人网站的建立（七）—— 那些建站必备的插件</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=609\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171104110123.png?imageView2/1/w/200/h/123\" alt=\"谈谈个人网站的建立（七）—— 那些建站必备的插件\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">欢迎访问我的网站[http://www.wenzhihuai.com/](http://www.wenzhihuai.com/) 。感谢，如果可以，希望能在GitHub上给个star，GitHub地址[https://github.com/...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-29</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 179浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=609\">\n" +
            "                            <span id=\"sourceId::609\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=601\"\n" +
            "                               title=\"谈谈个人网站的建立（六）—— 数据库同步\">谈谈个人网站的建立（六）—— 数据库同步</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=601\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171120100237.png?imageView2/1/w/200/h/123\" alt=\"谈谈个人网站的建立（六）—— 数据库同步\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">先来回顾一下上一篇的小集群架构，tomcat集群，nginx进行反向代理，服务器异地：\n" +
            "<div align=\"center\">\n" +
            "\n" +
            "![](http://image.wenzhihuai.com/images/201710180514...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-20</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 104浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=601\">\n" +
            "                            <span id=\"sourceId::601\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=600\"\n" +
            "                               title=\"mytis\">mytis</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=600\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/contentnopicture.jpg?imageView2/1/w/200/h/123\" alt=\"mytis\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># idea允许报错运行\n" +
            "http://blog.csdn.net/ch717828/article/details/70595069...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-15</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 49浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=600\">\n" +
            "                            <span id=\"sourceId::600\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=45\">Tomcat源码<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=599\"\n" +
            "                               title=\"Tomcat9源码——编译环境搭建\">Tomcat9源码——编译环境搭建</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=599\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171115124023.png?imageView2/1/w/200/h/123\" alt=\"Tomcat9源码——编译环境搭建\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># Readme\n" +
            "Tomcat 9 源码要求：  \n" +
            "1.jdk1.8+\n" +
            "\n" +
            "\n" +
            "# 步骤\n" +
            "1.直接下载源码\n" +
            "<div align=\"center\">\n" +
            "\n" +
            "![](http://image.wenzhihuai.com/image...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-15</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 55浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=599\">\n" +
            "                            <span id=\"sourceId::599\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=0\">生活笔记<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=598\"\n" +
            "                               title=\"这是一个测试\">这是一个测试</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=598\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/contentnopicture.jpg?imageView2/1/w/200/h/123\" alt=\"这是一个测试\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">这是一个测试...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-12</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 92浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=598\">\n" +
            "                            <span id=\"sourceId::598\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=44\">Spring源码<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=597\"\n" +
            "                               title=\"Spring项目路径\">Spring项目路径</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=597\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171112050154.png?imageView2/1/w/200/h/123\" alt=\"Spring项目路径\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\"># Spring源码——WebUtils\n" +
            "个人网站中部署的服务器共有两台，由于是分布式的环境，为了防止有效的针对具体某个服务器出现的问题，需要在网页上加上服务器的IP地址和项目启动的时间，但是由于资源的问题，最好还是不要使用ajax，毕竟...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-12</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 67浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=597\">\n" +
            "                            <span id=\"sourceId::597\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=594\"\n" +
            "                               title=\"谈谈个人网站的建立（五）—— 小集群的部署\">谈谈个人网站的建立（五）—— 小集群的部署</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=594\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171105050849.png?imageView2/1/w/200/h/123\" alt=\"谈谈个人网站的建立（五）—— 小集群的部署\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">欢迎访问我的个人网站O(∩_∩)O哈哈~希望大佬们能给个star，个人网站网址：[http://www.wenzhihuai.com](http://www.wenzhihuai.com)，个人网站代码地址：[https://github....</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-06</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 239浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=594\">\n" +
            "                            <span id=\"sourceId::594\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=0\">生活笔记<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=591\"\n" +
            "                               title=\"记11月4号\">记11月4号</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=591\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171104105733.png?imageView2/1/w/200/h/123\" alt=\"记11月4号\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">好困啊==，存储过程搞起来...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-04</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 72浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=591\">\n" +
            "                            <span id=\"sourceId::591\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                <article class=\"excerpt\">\n" +
            "                    <header>\n" +
            "                        <a class=\"label label-important\"\n" +
            "                           href=\"getcategory.html?categoryid=1\">java<i\n" +
            "                                class=\"label-arrow\"></i></a>\n" +
            "                        <h2><a href=\"getblogdetail.html?blogid=580\"\n" +
            "                               title=\"分布式学习（一）——基于ZooKeeper的队列爬虫\">分布式学习（一）——基于ZooKeeper的队列爬虫</a>\n" +
            "                        </h2>\n" +
            "                    </header>\n" +
            "                    <div class=\"focus\"><a href=\"getblogdetail.html?blogid=580\">\n" +
            "                        <img class=\"thumb\" src=\"http://image.wenzhihuai.com/images/20171030085127.png?imageView2/1/w/200/h/123\" alt=\"分布式学习（一）——基于ZooKeeper的队列爬虫\"></a>\n" +
            "                    </div>\n" +
            "                    <span class=\"note\">zookeeper\n" +
            "一直琢磨着分布式的东西怎么搞，公司也没有相关的项目能够参与，所以还是回归自己的专长来吧——基于ZooKeeper的分布式队列爬虫，由于没什么人能够一起沟通分布式的相关知识，下面的小项目纯属“胡编乱造”。\n" +
            "简单介绍下[...</span>\n" +
            "                    <p class=\"auth-span\">\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-clock-o\"></i> 2017-11-02</span>\n" +
            "                        <span class=\"muted\"><i class=\"fa fa-eye\"></i> 125浏览</span>\n" +
            "                        <span class=\"muted\">\n" +
            "                            <a href=\"/getblogdetail.html?blogid=580\">\n" +
            "                            <span id=\"sourceId::580\" class=\"cy_cmt_count\"></span>\n" +
            "                                <!--畅言导致加载缓慢，有时间处理一下-->\n" +
            "                            <script id=\"cy_cmt_num\"\n" +
            "                                    src=\"https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG\">\n" +
            "                            </script>评论</a>\n" +
            "                        </span>\n" +
            "\n" +
            "                </article>\n" +
            "\n" +
            "            \n" +
            "                            \n" +
            "                <div class=\"pagination\" style=\"background: transparent\">\n" +
            "                    <ul>\n" +
            "                        <li>\n" +
            "                            <a href=\"/index.html?pagenum=1\">首页</a>\n" +
            "                        </li>\n" +
            "                        <li class=\"prev-page\"><a\n" +
            "                                href=\"/index.html?pagenum=0\">上一页</a>\n" +
            "                        </li>\n" +
            "                        \n" +
            "                            \n" +
            "                                \n" +
            "                                    <li class=\"active\"><span> 1 </span></li>\n" +
            "                                \n" +
            "                                \n" +
            "                            \n" +
            "                        \n" +
            "                            \n" +
            "                                \n" +
            "                                \n" +
            "                                    <li>\n" +
            "                                        <a href=\"/index.html?pagenum=2\">2</a>\n" +
            "                                    </li>\n" +
            "                                \n" +
            "                            \n" +
            "                        \n" +
            "                            \n" +
            "                                \n" +
            "                                \n" +
            "                                    <li>\n" +
            "                                        <a href=\"/index.html?pagenum=3\">3</a>\n" +
            "                                    </li>\n" +
            "                                \n" +
            "                            \n" +
            "                        \n" +
            "                            \n" +
            "                                \n" +
            "                                \n" +
            "                                    <li>\n" +
            "                                        <a href=\"/index.html?pagenum=4\">4</a>\n" +
            "                                    </li>\n" +
            "                                \n" +
            "                            \n" +
            "                        \n" +
            "                            \n" +
            "                                \n" +
            "                                \n" +
            "                                    <li>\n" +
            "                                        <a href=\"/index.html?pagenum=5\">5</a>\n" +
            "                                    </li>\n" +
            "                                \n" +
            "                            \n" +
            "                        \n" +
            "                        \n" +
            "                            <li class=\"next-page\"><a\n" +
            "                                    href=\"/index.html?pagenum=2\">下一页</a>\n" +
            "                            </li>\n" +
            "                        \n" +
            "                        <li>\n" +
            "                            <a href=\"/index.html?pagenum=31\">末页</a>\n" +
            "                        </li>\n" +
            "                        <li>\n" +
            "                            <a>共31页</a>\n" +
            "                        </li>\n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "            \n" +
            "        </div>\n" +
            "    </div>\n" +
            "    \n" +
            "\n" +
            "\n" +
            "\n" +
            "<link href=\"http://image.wenzhihuai.com/newlypublished.css?ver=20171017\" rel=\"stylesheet\">\n" +
            "<script>\n" +
            "    $.ajax({\n" +
            "        url: \"/blogbyhits.do\",\n" +
            "        type: \"get\",\n" +
            "        dataType: 'json',\n" +
            "        success: function (data) {\n" +
            "            var html = \"\";\n" +
            "            var guessyourlike = $(\".guessyourlike\");\n" +
            "            for (var i = 0; i < data.length; i++) {\n" +
            "                html += \"    <li><a href=\\\"\" + \"/getblogdetail.html?blogid=\" + data[i].blogid + \"\\\"\\n\" +\n" +
            "                    \"    title=\\\"\" + data[i].title + \"\\\">\\n\" +\n" +
            "                    \"        <span class=\\\"thumbnail\\\"><img src=\\\"\" + data[i].imageurl + \"\\\" alt=\\\"\" + data[i].title + \"\\\" width=\\\"100px\\\"></span>\\n\" +\n" +
            "                    \"        <span class=\\\"text\\\">\" + data[i].title + \"</span><span class=\\\"muted\\\">\" + data[i].createAt + \"</span>\\n\" +\n" +
            "                    \"    <span class=\\\"muted\\\">\" + data[i].hits + \"浏览</span></a></li>\\n\";\n" +
            "            }\n" +
            "            guessyourlike.append(html);\n" +
            "        },\n" +
            "        error: function (e) {\n" +
            "        }\n" +
            "    });\n" +
            "    $.ajax({\n" +
            "        url: \"/getjsonbycategories.do\",\n" +
            "        type: \"get\",\n" +
            "        dataType: 'json',\n" +
            "        success: function (data) {\n" +
            "            var html = \"\";\n" +
            "            var fenlei = $(\"#fenlei\");\n" +
            "            for (var i = 0; i < data.length; i++) {\n" +
            "                html += \"<a href=\\\"/tech.html?categoryid=\" + data[i].cId + \"\\\">\\n\" +\n" +
            "                    data[i].cName + \"</a>\\n\"\n" +
            "            }\n" +
            "            fenlei.append(html);\n" +
            "        },\n" +
            "        error: function (e) {\n" +
            "        }\n" +
            "    });\n" +
            "    //    <a href=\"http://www.ibloger.net/tags-12.html\" class=\"tag-link-12\" title=\"214个话题\"\n" +
            "    //    style=\"font-size: 13px;\"> Java </a>\n" +
            "\n" +
            "    $.ajax({\n" +
            "        url: \"/biaoqianyun.do\",\n" +
            "        type: \"get\",\n" +
            "        dataType: 'json',\n" +
            "        success: function (data) {\n" +
            "            var html = \"\";\n" +
            "            var fenlei = $(\".tags-box\");\n" +
            "            for (var i = 0; i < data.length; i++) {\n" +
            "                html += \"<a href=\\\"/tech.html?tid=\" + data[i].key + \"\\\">\\n\" +\n" +
            "                    data[i].value + \"</a>\\n\"\n" +
            "            }\n" +
            "            fenlei.append(html);\n" +
            "        },\n" +
            "        error: function (e) {\n" +
            "        }\n" +
            "    });\n" +
            "\n" +
            "    //link\n" +
            "    $.ajax({\n" +
            "        url: \"/links.do\",\n" +
            "        type: \"get\",\n" +
            "        dataType: 'json',\n" +
            "        success: function (data) {\n" +
            "            var html = \"\";\n" +
            "            var fenlei = $(\".blogroll\");\n" +
            "            for (var i = 0; i < data.length; i++) {\n" +
            "                html += \"<li><a href=\" + data[i].url + \">\" +\n" +
            "                    data[i].name + \"</a></li>\\n\"\n" +
            "            }\n" +
            "            fenlei.append(html);\n" +
            "        },\n" +
            "        error: function (e) {\n" +
            "        }\n" +
            "    });\n" +
            "    $(document).ready(function () {\n" +
            "        setTimeout(function () {\n" +
            "            var navOffset = $(\".widgetRoller\").offset().top;\n" +
            "            $(window).scroll(function () {\n" +
            "                var scrollPos = $(window).scrollTop();\n" +
            "                if (scrollPos >= navOffset) {\n" +
            "                    $(\".widgetRoller\").css({position: \"fixed\", top: 0, zIndex: 0, width: 330}).fadeIn(100);\n" +
            "                } else {\n" +
            "                    $(\".widgetRoller\").removeAttr(\"style\");\n" +
            "                }\n" +
            "            });\n" +
            "        }, 2000);\n" +
            "    });\n" +
            "\n" +
            "    function test() {\n" +
            "        var div1 = $(\"#biaoqian\");\n" +
            "        if (div1.style.display == \"block\") {\n" +
            "            div1.style.display = \"none\";\n" +
            "        } else {\n" +
            "            div1.style.display = \"block\";\n" +
            "        }\n" +
            "    }\n" +
            "</script>\n" +
            "<aside class=\"sidebar\">\n" +
            "    <div class=\"widget widget_text\" style=\"height: 115px;margin-bottom: 0px\">\n" +
            "        <iframe src=\"http://www.seniverse.com/weather/weather.aspx?uid=U35799536E&cid=CHBJ000000&l=&p=SMART&a=1&u=C&s=13&m=\n" +
            "        0&x=1&d=0&fc=&bgc=2E93D9&bc=&ti=0&in=1&li=\" frameborder=\"0\" scrolling=\"no\" width=\"330\" height=\"115\"\n" +
            "                allowTransparency=\"true\"></iframe>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"widget d_tag\">\n" +
            "        <div class=\"title\"><h2>分类</h2></div>\n" +
            "        <div class=\"d_tags\" id=\"fenlei\" style=\"height: auto\">\n" +
            "            <a href=\"/life.html\">生活笔记</a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    <div class=\"widget d_postlist\">\n" +
            "        <div class=\"title\"><h2>猜你喜欢</h2></div>\n" +
            "        <ul class=\"guessyourlike\">\n" +
            "        </ul>\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    <div class=\"widget widget_text\">\n" +
            "        <div class=\"title\"><h2>新浪微博</h2></div>\n" +
            "        <div class=\"textwidget\">\n" +
            "            <iframe width=\"100%\" height=\"550\" class=\"share_self\" frameborder=\"0\" scrolling=\"no\"\n" +
            "                    src=\"http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=0&noborder=0&isWeibo=1&isFans=1&uid=1925306000&verifier=5d4515d6&dpc=1\"></iframe>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"widget widget_links\">\n" +
            "        <div class=\"title\"><h2>友情链接</h2></div>\n" +
            "        <ul class=\"xoxo blogroll\" style=\"width: 332px;\">\n" +
            "            <li><a href=\"http://www.99banzou.com\" target=\"_blank\">99伴奏网</a></li>\n" +
            "            <li><a href=\"http://www.anotherhome.net\" target=\"_blank\">Anotherhome</a></li>\n" +
            "            <li><a href=\"http://zhouchenwen.com\" rel=\"acquaintance\" target=\"_blank\">ColdCoder</a></li>\n" +
            "            <li><a href=\"http://www.spotty.com.cn/\" target=\"_blank\">DevNews</a></li>\n" +
            "            <li><a href=\"http://www.findspace.name\" target=\"_blank\">Findspace</a></li>\n" +
            "            <li><a href=\"http://blog.hickwu.com/\" target=\"_blank\">Hick</a></li>\n" +
            "        </ul>\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "    <div class=\"widgetRoller\">\n" +
            "        <div class=\"widget widget_divTags\" id=\"divTags\">\n" +
            "            <div class=\"title\"><h2>标签云</h2></div>\n" +
            "            <ul class=\"widget_divTags_inner\">\n" +
            "                <div class=\"tags-box\">\n" +
            "                </div>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</aside>\n" +
            "</section>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<footer class=\"footer\">\n" +
            "    <div class=\"footer-inner\">\n" +
            "        <div style=\"float: right;margin-top: 0;width: 100px;height: 50px\">\n" +
            "            <a href=\"https://www.upyun.com\">\n" +
            "                <img src=\"/images/youpailogo6.png\" style=\"float: left;width: auto\"/>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "        <div class=\"copyright pull-left\" style=\"float: left\">\n" +
            "            <a href=\"\" title=\"Zephery\">Zephery</a> 版权所有丨改自<a\n" +
            "                href=\"http://yusi123.com/\"> 欲思 </a>主题丨基于 SSM 构建 © 2016-<script>\n" +
            "                var date = new Date();  //创建对象\n" +
            "                var y = date.getFullYear();     //获取年份\n" +
            "                document.write(y);\n" +
            "            </script>\n" +
            "            丨托管于\n" +
            "            <a rel=\"nofollow\">云存储</a>\n" +
            "            |\n" +
            "            <a rel=\"nofollow\" target=\"_blank\" href=\"http://www.wenzhihuai.com:8081/admin/index.html\">后台管理 </a>\n" +
            "            丨\n" +
            "            <a rel=\"nofollow\" target=\"_blank\"\n" +
            "               href=\"http://www.miitbeian.gov.cn\">粤ICP备17092242号-1</a>\n" +
            "        </div>\n" +
            "        <div class=\"copyright pull-left\" style=\"float: left;\">\n" +
            "            本服务器IP地址：119.29.188.224\n" +
            " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 项目启动时间：2018-02-05 06:10:46\n" +
            "        </div>\n" +
            "        <div>\n" +
            "            <!-- cnzz stats -->\n" +
            "            <script type=\"text/javascript\">\n" +
            "                var cnzz_s_tag = document.createElement('script');\n" +
            "                cnzz_s_tag.type = 'text/javascript';\n" +
            "                cnzz_s_tag.async = true;\n" +
            "                cnzz_s_tag.charset = 'utf-8';\n" +
            "                cnzz_s_tag.src = 'https://w.cnzz.com/c.php?id=1262457277&async=1';\n" +
            "                var root_s = document.getElementsByTagName('script')[0];\n" +
            "                root_s.parentNode.insertBefore(cnzz_s_tag, root_s);\n" +
            "            </script>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</footer>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
