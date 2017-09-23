<html>
<head>
    <title>CSS+JS实现一个DIV层的展开/折叠效果</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            text-align: center;
            font: 75% Verdana, Arial, Helvetica, sans-serif;
        }

        h1 {
            font: 125% Arial, Helvetica, sans-serif;
            text-align: left;
            font-weight: bolder;
            background: #555;
            padding: 3px;
            display: block;
            color: #99CC00
        }

        .class1 {
            width: 40%;
            background: #CCCCCC;
            position: relative;
            margin: 0 auto;
            padding: 5px;
        }

        span {
            position: absolute;
            right: 10px;
            top: 8px;
            cursor: pointer;
            color: yellow;
        }

        p {
            text-align: left;
            line-height: 20px;
            background: #555;
            padding: 3px;
            margin-top: 5px;
            color: #99CC00
        }

        #class1content {
            height: 256px;
            overflow: hidden
        }
    </style>
    <script>
        function $(element) {
            return element = document.getElementById(element);
        }

        function $D() {
            var d = $('class1content');
            var h = d.offsetHeight;
            var maxh = 300;

            function dmove() {
                h += 50; //层展开速度
                if (h >= maxh) {
                    d.style.height = '300px';
                    clearInterval(iIntervalId);
                } else {
                    d.style.display = 'block';
                    d.style.height = h + 'px';
                }
            }

            iIntervalId = setInterval(dmove, 2);
        }

        function $D2() {
            var d = $('class1content');
            var h = d.offsetHeight;
            var maxh = 300;

            function dmove() {
                h -= 50;//层收缩速度
                if (h <= 0) {
                    d.style.display = 'none';
                    clearInterval(iIntervalId);
                } else {
                    d.style.height = h + 'px';
                }
            }

            iIntervalId = setInterval(dmove, 2);
        }

        function $use() {
            var d = $('class1content');
            var sb = $('stateBut');
            if (d.style.display == 'none') {
                $D();
                sb.innerHTML = '收缩';
            } else {
                $D2();
                sb.innerHTML = '展开';
            }
        }
    </script>
</head>
<body>
<div class="class1">
    <h1>展开/折叠效果 - 代码家园</h1>
    <span id="stateBut" onclick="$use()">收缩</span>
    <p id="class1content">当然你也可以把下面一段代码去掉，只留这一行。
    </p>
</div>
</body>