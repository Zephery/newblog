<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="tripactive" value="active"/>
    <jsp:param name="title" value="旅行|多出门走走"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="js/waterfall/css/reset.css" rel="stylesheet"/>
    <link href="js/waterfall/css/main.css" rel="stylesheet"/>
    <link href="js/waterfall/css/woo.css" rel="stylesheet"/>
    <link href="js/waterfall/css/gotop.css" rel="stylesheet"/>
    <link href="js/waterfall/css/pagine.css" rel="stylesheet"/>
    <link href="js/waterfall/css/resize.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="js/mymodel/jquery.my-modal.1.1.winStyle.css"/>
    <script src="js/waterfall/js/browser.js"></script>
    <script src="js/waterfall/js/history.js"></script>
    <script src="js/waterfall/js/template.min.js"></script>
    <script src="js/waterfall/js/tabswitch.js"></script>
    <script src="js/waterfall/js/woo.js"></script>
    <script src="js/waterfall/js/masnunit.js"></script>
    <script type="text/javascript">
        $(function(){
            $.Woo();
        });
    </script>
    <style type="text/css">
        /* invisible holder for the page */
        #win-holder {
            overflow: hidden;
            height: 0px;
        }
    </style>
    <link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link rel="stylesheet" id="style-css" href="${pageContext.request.contextPath}/css/jingmi.css"
          type="text/css" media="all">
</head>
<body>
<div id="content">
    <div id="woo-holder">
        <!-- When gotop button is clicked, scrollbar will be positioned where the anchor is. -->
        <ul id="switchholder">
            <li class="woo-swa woo-cur"><a href="javascript:;">2017</a></li>
            <li class="woo-swa"><a href="javascript:;">2016</a></li>
            <li class="woo-swa"><a href="javascript:;">2015</a></li>
            <li class="woo-swa"><a href="javascript:;">2014</a></li>
        </ul>
        <div class="woo-swb">
            <!-- data-totalunits is set here, then pager nums would be fixed -->
            <!-- It would have been Hasnext Mode if you didn't do it -->
            <div class="woo-pcont woo-masned my-pic" data-totalunits="440">


            </div>
            <div class="woo-pager"></div>
        </div>
    </div>
    <!-- Woo holder over -->
</div>

<div id="win-holder">
    <form action="${pageContext.request.contextPath}/ajaxpic.html?page=" id="woo-form-album">
        <input name="user_id" type="hidden" value="48"/>
        <input name="pic" type="hidden" value="1"/>
    </form>
</div>
<script type="text/javascript" src="js/jQuery.autoIMG.js"></script>
<script>
    $(function () {
        var conf = {
            // Prefix of form id. Each form represents a subpage data request.
            "formprefix": '#woo-form-',

            // Identification of a single waterfall. There are 4 waterfalls in main.html.
            // You can get the form in #win-holder by putting formprefix and one identification together.
            // <form id="woo-form-(collect|original|album|favalbum)">
            "arrform": ['album'],
            unitsnum: 15,
            // arrsplit, the last part of the url which has been split by page number.
            // The first part of the url has already been put in the action attribute of the proper form.
            // Page number can be seen neighther in the first part nor the last part.
            // Why and how we do this? Think a simple url like '/message/list/1/tail/?type=hot'
            // First, we put '/message/list/' in form action.
            // Then, we can make a hidden input in this form <input type="hidden" name="type" value="hot"/>.
            // Finally, we put '/tail/' into arrsplit.
            // Or, we could choose no hidden input and '/tail/?type=hot' into arrsplit.
            // Nevertheless, we put them together and get the entire url.
            "arrsplit": ['/?type=hot', '', '', ''],

            // The extend width of each common column including margin between two cols.
            "arrmasnw": [245, 250, 250, 245],

            // The margin between two cols.
            // In this example, visible column width is 245-21=224.
            "arrmargin": [21, 42, 42, 21],

            // Diff value of special column width, default 0.
            // Why the value is 0, not [0,0,0,0]?
            // We simplified params by converting equal-value array [0,0,0,0] to single value 0.
            // Params arrsplit, arrmasnw, arrmargin and arrgap can be treated on the same principle.
            // But, param arrform is out of the principle.
            "arrfmasnw": 0,

            // The gap between units in one column.
            "arrgap": 0,

            "subpagenum": 4,
            // Install $gopre $gonext $gotop as you like.
            "gopre": '#gopre',
            "gonext": '#gonext',
            "gotop": '#gotop',

            // Footer selctor, any node below the pager can be treated as footer.
            "footer": '#footer,#preserve',

            // Open resize mode.
            // If you open it, every time window resizes, waterfall will be repainted.
            // Not recommend in ie.
            "resize": true,
            "lbias": 400   // 400px load a new page
            // Sth you do during scrolling, say, pop out a login confirm.
//            "onScroll": function (tp) {
            // tp current scrolltop
//			if( (typeof ALREADYNOTICED === 'undefined' || !ALREADYNOTICED) && tp >= 1000 ){
//				ALREADYNOTICED = true;
//				alert("login");
//			}
//            }
        };

        // Let's go.
        $.Woo(conf);
    });
    $(function () {
    });
</script>

<div class="m-modal">
    <div class="m-modal-dialog">
        <div class="m-top">
            <h4 class="m-modal-title"></h4>
            <span class="m-modal-close">&times;</span>
        </div>
        <div class="m-left">
            <img src="" id="imgId"/>
        </div>
        <div class="m-right">
            <div style="width: 90%;margin: 0 auto">
                <!--畅言-->
                <!--PC和WAP自适应版-->
                <div id="SOHUCS" sid=""></div>
                <script type="text/javascript">
                    (function () {
                        var appid = 'cyt4SnwiG';
                        var conf = '9c120d8118078b7ea3a728dc120a28da';
                        var width = window.innerWidth || document.documentElement.clientWidth;
                        if (width < 960) {
                            window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>');
                        } else {
                            var loadJs = function (d, a) {
                                var c = document.getElementsByTagName("head")[0] || document.head || document.documentElement;
                                var b = document.createElement("script");
                                b.setAttribute("type", "text/javascript");
                                b.setAttribute("charset", "UTF-8");
                                b.setAttribute("src", d);
                                if (typeof a === "function") {
                                    if (window.attachEvent) {
                                        b.onreadystatechange = function () {
                                            var e = b.readyState;
                                            if (e === "loaded" || e === "complete") {
                                                b.onreadystatechange = null;
                                                a()
                                            }
                                        }
                                    } else {
                                        b.onload = a
                                    }
                                }
                                c.appendChild(b)
                            };
                            loadJs("https://changyan.sohu.com/upload/changyan.js", function () {
                                window.changyan.api.config({appid: appid, conf: conf})
                            });
                        }
                    })(); </script>
                <!--畅言end-->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/mymodel/jquery.my-modal.1.1.js"></script>
<script>
    function show(a) {
        var m1 = new MyModal.modal(function () {
        });
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/imagedetail.html?imageid=' + a,
            timeout: 20000,
            success: function (data) {
                var data = $.parseJSON(data);
                $("#imgId").attr('src', data.imagepath);
                $('.m-modal-title').html(data.imagename);
                $(".m-left").autoIMG();
                $("#SOHUCS").attr('sid', data.imageid);
            },
            error: function (data) {
                alert(data)
            }
        });
        m1.show();
    }
</script>
<script src="js/center.js"></script>
<script>
    (function ($) {
        $(document).ready(function () {
            var win_width = $(window).width();
            var mleft=$(".m-left");
            if (win_width < 500) {
                mleft.autoIMG();
            } else {
                mleft.center();
            }
        });
    })(jQuery);
</script>
<%@include file="foot.jsp"%>
</body>
</html>