<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta content="" name="description"/>
    <meta content="webthemez" name="author"/>
    <title>日志系统</title>
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="js/font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet"/>
    <link href="js/morris/morris-0.4.3.min.css" rel="stylesheet"/>
    <link href="css/custom-styles.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="js/Lightweight-Chart/cssCharts.css">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.metisMenu.js"></script>
    <script src="js/morris/raphael-2.1.0.min.js"></script>
    <script src="js/morris/morris.js"></script>
    <script src="js/easypiechart.js"></script>
    <script src="js/easypiechart-data.js"></script>
    <script src="js/Lightweight-Chart/jquery.chart.js"></script>
    <script src="js/custom-scripts.js"></script>
    <script type="text/javascript" src="js/chart.min.js"></script>
    <script type="text/javascript" src="js/chartjs.js"></script>
</head>

<body>
<div id="page-inner">
    <div class="row">
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-default chartJs">
                <div class="panel-heading">
                    <div class="card-title">
                        <div class="title">Line Chart</div>
                    </div>
                </div>
                <div class="panel-body">
                    <canvas id="line-chart" class="chart"></canvas>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-xs-12">
            <div class="panel panel-default chartJs">
                <div class="panel-heading">
                    <div class="card-title">
                        <div class="title">Bar Chart</div>
                    </div>
                </div>
                <div class="panel-body">
                    <canvas id="bar-chart" class="chart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>Profit</h4>
                    <div class="easypiechart" id="easypiechart-blue" data-percent="82"><span
                            class="percent">82%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>Sales</h4>
                    <div class="easypiechart" id="easypiechart-orange" data-percent="55"><span
                            class="percent">55%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>Customers</h4>
                    <div class="easypiechart" id="easypiechart-teal" data-percent="84"><span
                            class="percent">84%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>No. of Visits</h4>
                    <div class="easypiechart" id="easypiechart-red" data-percent="46"><span
                            class="percent">46%</span>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->

    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Line Chart
                </div>
                <div class="panel-body">
                    <div id="morris-line-chart"></div>
                </div>
            </div>
        </div>

        <div class="col-md-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Bar Chart Example
                </div>
                <div class="panel-body">
                    <div id="morris-bar-chart"></div>
                </div>

            </div>
        </div>

    </div>


    <div class="row">
        <div class="col-md-9 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Area Chart
                </div>
                <div class="panel-body">
                    <div id="morris-area-chart"></div>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Donut Chart Example
                </div>
                <div class="panel-body">
                    <div id="morris-donut-chart"></div>
                </div>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-md-12">

        </div>
    </div>
</div>

</body>
</html>