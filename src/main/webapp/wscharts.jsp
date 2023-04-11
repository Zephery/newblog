<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/11/24
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="logactive" value="active"/>
    <jsp:param name="title" value="测试"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/highcharts/10.0.0/highcharts.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/echarts/5.3.2/echarts.min.js"></script>
</head>
<body>
<div class="panel-heading" style="background-color: rgba(187,255,255,0.7)">
    <div class="card-title">
        <strong>CPU使用率</strong>
    </div>
</div>
<div class="panel-body">
    <div id="cpu" style="width: auto;height: 330px"></div>
    <!--JVM-->
    <script type="text/javascript">

        $(function () {
            var ws;
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

            function activeLastPointToolip(chart) {
                var points = chart.series[0].points;
                chart.tooltip.refresh(points[points.length - 1]);
            }

            var wsUri = "ws://localhost:9090/wscpu.ws";
            var result;
            //                                                writeToScreen("Connecting to " + wsUri);
            ws = new WebSocket(wsUri);
            ws.onopen = function (evt) {
            };
            ws.onmessage = function (evt) {
                result = evt.data;
            };
            ws.onclose = function (event) {
            };
            ws.onerror = function (evt) {
            };
            $('#cpu').highcharts({
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    events: {
                        load: function () {
                            var series = this.series[0],
                                chart = this;
                            setInterval(function () {
                                console.log(result);
                                var x = (new Date()).getTime(); // current time
                                series.addPoint([x, result], true, true);
                                activeLastPointToolip(chart)
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
                yAxis: [{
                    title: {
                        text: null
                    },
                    max: 100,
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                }, {
                    title: {
                        text: null
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                }],
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2) + '%';
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                series: [{
                    name: '已使用',
                    marker: {
                        enabled: false
                    },
                    data: (function () {
                        // generate an array of random data
                        var data = [],
                            time = (new Date()).getTime(),
                            i = -50;
                        <c:forEach var="cpu" items="${cpu_usage}">
                        data.push({
                            x: time + i * 1000,
                            y: ${cpu}
                        });
                        i++;
                        </c:forEach>
                        return data;
                    }())
                }]
            }, function (c) {
                activeLastPointToolip(c)
            });
        });
    </script>
</div>
</body>
</html>