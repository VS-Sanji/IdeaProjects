<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.oa3.action.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
    <%--设置整个网页的基础路径是：http：//localhost：8080/oajsp/ --%>
    <%--设置了这个，在这个jsp中所有的需要加上根路径的都可以省略，访问时会自动添加上这个 base--%>
    <%--    <base href="http://localhost:8080/oajsp/">--%>
    <%--base路径可以动态的写--%>
    <%--${pageContext.request.scheme} 获取 http --%>
    <%--${pageContext.request.serverName} 获取 服务器名称 这里是localhost--%>
    <%--${pageContext.request.serverPort} 获取端口号--%>
    <%--${pageContext.request.contextPath} 获取项目名--%>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>

<%--显示一个登录名--%>
<h2>欢迎${username}</h2>
<%--写一个安全退出登录--%>
<a href="${pageContext.request.contextPath}/user/exit">[安全退出]</a>

<script type="text/javascript">
    function del(dno) {
        if (window.confirm("确定删除吗？")) {
            //注意：html的base标签可能对JS代码不起作用，所以JS代码最好写上“/oajsp”，不要动JS的代码
            document.location.href = "${pageContext.request.contextPath}/dept/delete?deptno=" + dno
        }
    }
</script>
<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${depts}" varStatus="deptStatus" var="dept">
        <tr>
            <td>${deptStatus.count}</td>
            <td>${dept.deptno}</td>
            <td>${dept.dname}}</td>
            <td>
                <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
                <a href="dept/edit?deptno=${dept.deptno}">修改</a>
                <a href="dept/detail?deptno=${dept.deptno}">详情</a>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
<hr>
<a href="add.jsp">新增部门</a>

