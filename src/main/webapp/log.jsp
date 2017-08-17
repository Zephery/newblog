<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="logactive" value="active"/>
    <jsp:param name="title" value="日志系统"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>日志系统</title>
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.6.2/echarts.min.js"></script>
    <script type="text/javascript" src="js/china.js"></script>
    <!--不要导入jquery，扇形图有冲突-->
    <style>
        th {
            text-align: center;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body class="home blog hPC">
<section class="container">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-5" style="width: 100%;padding-bottom: 20px">
                <div class="panel panel-default" style="margin-bottom: 0px">
                    <table class="table table-bordered">
                        <tr style="background-color: rgba(187,255,255,0.7)">
                            <th>您的IP</th>
                            <th style="text-align: center">您的地址</th>
                            <th style="text-align: center">统计时间范围</th>
                            <th style="text-align: center">本站上周总PV</th>
                            <th style="text-align: center">本站上周总UV</th>
                        </tr>
                        <tbody>
                        <tr>
                            <td>${ip}</td>
                            <td>${yourcity}</td>
                            <td>${fn:replace(daterange.get(0),"\"","")}至${fn:replace(daterange.get(daterange.size()-1),"\"","")}</td>
                            <td>${pv_sum}</td>
                            <td>${uv_sum}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 col-xs-12">
                <div class="panel panel-default chartJs">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <div class="card-title">
                            <strong>PV和UV折线图</strong>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="linecontainer" style="width: auto;height: 330px"></div>
                        <script>
                            /**
                             * Highcharts 在 4.2.0 开始已经不依赖 jQuery 了，直接用其构造函数既可创建图表
                             **/
                            var chart = new Highcharts.Chart('linecontainer', {
                                title: {
                                    text: null
                                },
                                credits: {
                                    enabled: false
                                },
                                xAxis: {
                                    categories: ${daterange}
                                },
                                yAxis: {
                                    title: {
                                        text: '次数'
                                    },
                                    plotLines: [{
                                        value: 0,
                                        width: 1,
                                        color: '#808080'
                                    }]
                                },
                                tooltip: {
                                    valueSuffix: '次'
                                },
                                legend: {
                                    borderWidth: 0,
                                    align: "center", //程度标的目标地位
                                    verticalAlign: "top", //垂直标的目标地位
                                    x: 0, //间隔x轴的间隔
                                    y: 0 //间隔Y轴的间隔
                                },
                                series: [{
                                    name: 'pv',
                                    data:${pv_count}
                                }, {
                                    name: 'uv',
                                    data:${visitor_count}
                                }, {
                                    name: 'ip_count',
                                    data:${ip_count}
                                }
                                ]
                            })
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-xs-12">
                <div class="panel panel-default chartJs">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <div class="card-title">
                            <strong>平均访问时长和跳出率</strong>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="avgtimecontainer" style="width: auto;height: 330px"></div>
                        <script>
                            /**
                             * Highcharts 在 4.2.0 开始已经不依赖 jQuery 了，直接用其构造函数既可创建图表
                             **/
                            var chart = new Highcharts.Chart('avgtimecontainer', {
                                title: {
                                    text: null
                                },
                                chart: {
                                    marginRight: 80 // like left
                                },
                                credits: {
                                    enabled: false
                                },
                                xAxis: {
                                    categories: ${daterange}
                                },
                                yAxis: [{
                                    lineWidth: 1,
                                    title: {
                                        text: '时长(单位:s)'
                                    }

                                }, {
                                    lineWidth: 1,
                                    opposite: true,
                                    title: {
                                        text: '跳出率'
                                    }
                                }],
                                tooltip: {
                                    valueSuffix: ''
                                },
                                legend: {
                                    borderWidth: 0,
                                    align: "center", //程度标的目标地位
                                    verticalAlign: "top", //垂直标的目标地位
                                    x: 0, //间隔x轴的间隔
                                    y: 0 //间隔Y轴的间隔
                                },
                                series: [{
                                    name: '平均访问时长',
                                    data:${avg_visit_time}
                                }, {
                                    name: '跳出率',
                                    data:${bounce_ratio},
                                    yAxis: 1
                                }
                                ]
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <strong>来源</strong>
                    </div>
                    <div class="panel-body">
                        <div id="eeecontainer" style="width: auto;height: 421px;"></div>
                        <script>
                            $(function () {
                                $('#eeecontainer').highcharts({
                                    chart: {
                                        plotBackgroundColor: null,
                                        plotBorderWidth: null,
                                        plotShadow: false
                                    },
                                    credits: {
                                        enabled: false
                                    },
                                    title: {
                                        text: '来源扇形图'
                                    },
                                    tooltip: {
                                        headerFormat: '{series.name}<br>',
                                        pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
                                    },
                                    plotOptions: {
                                        pie: {
                                            allowPointSelect: true,
                                            cursor: 'pointer',
                                            dataLabels: {
                                                enabled: true,
                                                format: '<b>{point.name}</b>: {point.percentage:.2f} %',
                                                style: {
                                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                                }
                                            }
                                        }
                                    },
                                    series: [{
                                        type: 'pie',
                                        name: '浏览器访问量占比',
                                        data: [
                                            <c:forEach var="fan" items="${sourcelist}">
                                            ['${fan.name}', ${fan.pv_ratio}],
                                            </c:forEach>
                                        ]
                                    }]
                                });
                            });
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <strong>地域</strong>
                    </div>
                    <div class="panel-body">
                        <div id="diyu" style="width: 100%;height: 421px"></div>
                        <script type="text/javascript">
                            // 基于准备好的dom，初始化echarts实例
                            var myChart = echarts.init(document.getElementById('diyu'));

                            function randomData() {
                                return Math.round(Math.random() * 1000);
                            }

                            option = {
                                tooltip: {
                                    trigger: 'item'
                                },
                                legend: {
                                    orient: 'vertical',
                                    left: 'left'
                                },
                                visualMap: {
                                    min: 0,
                                    max:${diyumax},
                                    left: 'left',
                                    top: 'bottom',
                                    text: ['高', '低'],           // 文本，默认为数值文本
                                    calculable: true
                                },
                                toolbox: {
                                    show: true,
                                    orient: 'vertical',
                                    left: 'right',
                                    top: 'center',
                                    feature: {
                                        dataView: {readOnly: false},
                                        restore: {},
                                        saveAsImage: {}
                                    }
                                },
                                series: [
                                    {
                                        name: '访问量',
                                        type: 'map',
                                        mapType: 'china',
                                        roam: false,
                                        label: {
                                            normal: {
                                                show: true
                                            },
                                            emphasis: {
                                                show: true
                                            }
                                        },
                                        data: [
                                            <c:forEach var="to" items="${diyu}">
                                            {name: '${to.name}', value: ${to.pv_count}},
                                            </c:forEach>
                                        ]
                                    }
                                ]
                            };
                            myChart.setOption(option);
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9 col-sm-12 col-xs-12" style="width: 100%">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <strong>前${topTens.size()}受访页面</strong>
                    </div>
                    <table class="table table-bordered" style="width: 100%">
                        <tr>
                            <th style="width: 60%;text-align: left">链接</th>
                            <th style="text-align: center">PV</th>
                            <th style="text-align: center">UV</th>
                            <th style="text-align: center">平均停留时长(单位:s)</th>
                        </tr>
                        <tbody>
                        <c:forEach var="topten" items="${topTens}">
                            <tr>
                                <td style="text-align: left">${topten.name}</td>
                                <td>${topten.pv_count}</td>
                                <td>${topten.visitor_count}</td>
                                <td>${topten.average_stay_time}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9 col-sm-12 col-xs-12" style="width: 100%">
                <div class="panel panel-default">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <strong> 前${rukou.size()}入口页面</strong>
                    </div>
                    <table class="table table-bordered">
                        <tr>
                            <th style="text-align: left">链接</th>
                            <th>PV</th>
                            <th>UV</th>
                            <th>平均停留时长(单位:s)</th>
                        </tr>
                        <tbody>
                        <c:forEach var="topten" items="${rukou}">
                            <tr>
                                <td style="text-align: left">${topten.name}</td>
                                <td>${topten.pv_count}</td>
                                <td>${topten.visitor_count}</td>
                                <td>${topten.average_stay_time}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 col-xs-12">
                <div class="panel panel-default chartJs">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <div class="card-title">
                            <strong>JVM</strong>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="jvm" style="width: auto;height: 330px"></div>
                        <!--JVM-->
                        <script type="text/javascript">
                            Highcharts.setOptions({
                                global: {
                                    useUTC: false
                                }
                            });

                            function activeLastPointToolip(chart) {
                                var points = chart.series[0].points;
                                chart.tooltip.refresh(points[points.length - 1]);
                            }

                            $('#jvm').highcharts({
                                chart: {
                                    type: 'spline',
                                    animation: Highcharts.svg, // don't animate in old IE
                                    marginRight: 10,
                                    events: {
                                        load: function () {
                                            // set up the updating of the chart each second
                                            var series = this.series[0],
                                                chart = this;
                                            setInterval(function () {
                                                $.ajax({
                                                    type: "GET",  //提交方式
                                                    url: "${pageContext.request.contextPath}/jmx.do?" + (new Date).getTime(),//路径
                                                    success: function (result) {//返回数据根据结果进行相应的处理
                                                        var x = (new Date()).getTime(); // current time
                                                        var y = result;
                                                        console.log([x, y]);
                                                        series.addPoint([x, y], true, true);
                                                        activeLastPointToolip(chart)
                                                    },
                                                    dataType: 'json'
                                                });

                                            }, 1000);
                                        }
                                    }
                                },
                                title: {
                                    text: null
                                },
                                xAxis: {
                                    type: 'datetime',
                                    tickPixelInterval: 150
                                },
                                credits: {
                                    enabled: false
                                },
                                yAxis: {
                                    title: {
                                        text: '值'
                                    },
                                    plotLines: [{
                                        value: 0,
                                        width: 1,
                                        color: '#808080'
                                    }]
                                },
                                tooltip: {
                                    formatter: function () {
                                        return '<b>' + this.series.name + '</b><br/>' +
                                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                            Highcharts.numberFormat(this.y, 2);
                                    }
                                },
                                legend: {
                                    enabled: false
                                },
                                exporting: {
                                    enabled: false
                                },
                                series: [{
                                    name: 'JVM',
                                    data: (function () {
                                        // generate an array of random data
                                        var data = [],
                                            time = (new Date()).getTime(),
                                            i = -15;
                                        <c:forEach var="jmx" items="${jmx_memory_use}">
                                        data.push({
                                            x: time + i * 1000,
                                            y: ${jmx}
                                        });
                                        i++;
                                        </c:forEach>
                                        return data;
                                    }())
                                }]
                            }, function (c) {
                                activeLastPointToolip(c)
                            });

                        </script>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-xs-12">
                <div class="panel panel-default chartJs">
                    <div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
                        <div class="card-title">
                            <strong>平均访问时长和跳出率</strong>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="fwefawe" style="width: auto;height: 330px"></div>
                        <script>
                            /**
                             * Highcharts 在 4.2.0 开始已经不依赖 jQuery 了，直接用其构造函数既可创建图表
                             **/
                            var chart = new Highcharts.Chart('fwefawe', {
                                title: {
                                    text: null
                                },
                                chart: {
                                    marginRight: 80 // like left
                                },
                                credits: {
                                    enabled: false
                                },
                                xAxis: {
                                    categories: ${daterange}
                                },
                                yAxis: [{
                                    lineWidth: 1,
                                    title: {
                                        text: '时长(单位:s)'
                                    }

                                }, {
                                    lineWidth: 1,
                                    opposite: true,
                                    title: {
                                        text: '跳出率'
                                    }
                                }],
                                tooltip: {
                                    valueSuffix: ''
                                },
                                legend: {
                                    borderWidth: 0,
                                    align: "center", //程度标的目标地位
                                    verticalAlign: "top", //垂直标的目标地位
                                    x: 0, //间隔x轴的间隔
                                    y: 0 //间隔Y轴的间隔
                                },
                                series: [{
                                    name: '平均访问时长',
                                    data:${avg_visit_time}
                                }, {
                                    name: '跳出率',
                                    data:${bounce_ratio},
                                    yAxis: 1
                                }
                                ]
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
<jsp:include page="foot.jsp"/>