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
<script src="assets/js/jquery-3.1.0.js" type="text/javascript"/>
<head>
    <title>test converter</title>
</head>
<body>
<div id="resp"></div>
<input type="button" onclick="req()" value="请求"/>
<script>
    function req() {
        $.ajax({
            url: "convert",
            data: "1-jack",
            type: "POST",
            contentType: "application/x-wisely",
            success: function (data) {
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>