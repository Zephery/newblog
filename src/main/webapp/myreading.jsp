<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/10/9
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<style>
    .myreading td {
        color: white;
    }

    .table-bordered thead tr th {
        background: transparent;
        text-align: center;
        font-weight: bolder;
        color: white;
    }
</style>
<script>
    $.ajax({
        url: "${pageContext.request.contextPath}/myreading.do",
        type: "get",
        dataType: 'json',
        success: function (data) {
            var html = "";
            var fenlei = $(".myreading");
            for (var i = 0; i < data.length; i++) {
                html += "        <tr>\n" +
                    "            <td style=\"text-align: center\">" + parseInt(i + 1) + "</td>\n" +
                    "            <td style=\"text-align: center\">" + data[i].title + "</td>\n" +
                    "            <td style=\"text-align: center\">" + data[i].author + "</td>\n" +
                    "            <td style=\"text-align: center\">" + data[i].bookindex + "</td>\n" +
                    "            <td style=\"text-align: center\">" + data[i].rentdate + "</td>\n" +
                    "            <td style=\"text-align: center\">" + data[i].returndate + "</td>\n" +
                    "        </tr>"
            }
            fenlei.append(html);
        },
        error: function (e) {
        }
    });
</script>

<table class="table table-bordered" style="width: 90%;margin: 0 auto;background: transparent">
    <thead>
    <tr>
        <th style="width: 5%">序号</th>
        <th>标题</th>
        <th>作者</th>
        <th style="width: 10%">索引号</th>
        <th style="width: 10%">借书日期</th>
        <th style="width: 10%">还书日期</th>
    </tr>
    </thead>
    <tbody class="myreading">
    </tbody>
</table>
