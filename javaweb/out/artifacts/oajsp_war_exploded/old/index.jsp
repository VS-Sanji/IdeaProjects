<%--
  Created by IntelliJ IDEA.
  User: Song yuhang
  Date: 2022/3/14
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<h1>login page</h1>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="text" name="password"><br>
    <input type="checkbox" name="f" value="1">十天内免登录<br>
    <input type="submit" value="login">

</form>
</body>
</html>
