<!DOCTYPE html>

<head>

    <meta charset="utf-8"/>

    <title>Reveal Demo</title>


    <!-- Attach our CSS -->

    <link rel="stylesheet" href="css/reveal.css">


    <!-- Attach necessary scripts -->

    <!-- <script type="text/javascript" src="jquery-1.4.4.min.js"></script> -->

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript" src="js/jquery.reveal.js"></script>


    <style type="text/css">

        body {
            font-family: "HelveticaNeue", "Helvetica-Neue", "Helvetica", "Arial", sans-serif;
        }

        .big-link {
            display: block;
            margin-top: 100px;
            text-align: center;
            font-size: 70px;
            color: #06f;
        }

    </style>


</head>

<body>


<a href="#" class="big-link" data-reveal-id="myModal">

    jquery1

</a>


<a href="#" class="big-link" data-reveal-id="myModal" data-animation="fade">

    jquery2

</a>


<a href="#" class="big-link" data-reveal-id="myModal" data-animation="none">

    jquery3

</a>


<div id="myModal" class="reveal-modal">

    <h1>jquery导出层</h1>

    <p>This is a default modal in all its glory, but any of the styles here can easily be changed in the CSS.</p>

    <a class="close-reveal-modal">&#215;</a>

</div>

</body>

</html>