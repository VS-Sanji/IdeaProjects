<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改页面</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/dept/modify" method="post">
    部门编号：<input type="text" name="deptno" value="<%=request.getAttribute("deptno")%>" readonly/><br>
    部门名称：<input type="text" name="dname" value="<%=request.getAttribute("dname")%>"/><br>
    部门位置：<input type="text" name="loc" value="<%=request.getAttribute("loc")%>"/><br>

    <input type="submit" value="修改"/>
</form>


</body>
</html>
