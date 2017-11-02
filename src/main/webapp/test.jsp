<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>jQuery响应式Pinterest样式无限动态加载图片瀑布流特效</title>
    <style>
        * {
            margin: 0;
        }

        .jq22-container {
            margin-top: 50px;
        }

        #div1 {
            margin: auto;
            position: relative;
        }

        .box {
            float: left;
            border: 0px solid #ccc;
            background: #f7f7f7;
            box-shadow: 0 0 8px #ccc;
        }

        .box:hover {
            box-shadow: 0 0 10px #999;
        }

        .box img {
            width: 240px;
        }
    </style>
    <!--[if IE]>
    <script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="jq22-container">
    <div class="jq22-content bgcolor-3">
        <div id="div1">
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/08.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/08.jpg" alt=""></div>
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/08.jpg" alt=""></div>
            <div class="box"><img src="img/01.jpg" alt=""></div>
            <div class="box"><img src="img/02.jpg" alt=""></div>
            <div class="box"><img src="img/03.jpg" alt=""></div>
            <div class="box"><img src="img/04.jpg" alt=""></div>
            <div class="box"><img src="img/05.jpg" alt=""></div>
            <div class="box"><img src="img/06.jpg" alt=""></div>
            <div class="box"><img src="img/07.jpg" alt=""></div>
            <div class="box"><img src="img/08.jpg" alt=""></div>
        </div>
    </div>
</div>
<script src="http://www.jq22.com/jquery/1.7.2/jquery.min.js"></script>
<script src="js/jquery.waterfall.js"></script>
<script>
    $("#div1").waterfall({
        itemClass: ".box",
        minColCount: 1,
        spacingWidth: 5,
        spacingHeight: 10,
        resizeable: true,
        ajaxCallback: function (success, end) {
            var data = {
                "data": [
                    {"src": "03.jpg"}, {"src": "04.jpg"}, {"src": "02.jpg"}, {"src": "05.jpg"}, {"src": "01.jpg"}, {"src": "06.jpg"}
                ]
            };
            var str = "";
            var templ = '<div class="box" style="opacity:0;filter:alpha(opacity=0);"><div class="pic"><img src="img/{{src}}" /></div></div>'

            for (var i = 0; i < data.data.length; i++) {
                str += templ.replace("{{src}}", data.data[i].src);
            }
            $(str).appendTo($("#div1"));
            success();
            end();
        }
    });
</script>
</body>
</html>