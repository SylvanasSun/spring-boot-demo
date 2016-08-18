<%--
  Created by IntelliJ IDEA.
  User: sylvanasp
  Date: 2016/7/24
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>SSE Test$</title>
</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="<c:url value="assets/js/jquery-3.1.0.js"/>"></script>
<script type="text/javascript">

    if (!!window.EventSource) {
        var source = new EventSource('push');
        s = '';
        source.addEventListener('message', function (e) {
            s += e.data + "<br/>";
            $("#msgFrompPush").html(s);
        });

        source.addEventListener('open', function (e) {
            console.log("连接打开.");
        }, false);

        source.addEventListener('error', function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("连接关闭.");
            } else {
                console.log(e.readyState);
            }
        }, false);

    } else {
        console.log("你的浏览器不支持SSE");
    }

</script>
</body>
</html>