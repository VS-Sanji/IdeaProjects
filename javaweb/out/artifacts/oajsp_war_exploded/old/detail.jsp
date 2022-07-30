<%@page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>详情页面</title>
</head>
<body>


部门编号: <%=request.getAttribute("deptno")%><br>
部门名称：<%=request.getAttribute("dname")%><br>
部门位置：<%=request.getAttribute("loc")%><br>
<input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>
