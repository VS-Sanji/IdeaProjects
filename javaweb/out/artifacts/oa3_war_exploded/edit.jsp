<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改页面</title>
</head>
<body>
<form action="list.html" method="get">
    部门编号：<input type="text" name="deptno" value="10" readonly/><br>
    部门名称：<input type="text" name="dname" value="销售部"/><br>
    部门位置：<input type="text" name="loc" value="北京"/><br>

    <input type="submit" value="修改"/>
</form>


</body>
</html>
