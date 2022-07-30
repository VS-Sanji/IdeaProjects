<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.oa3.action.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
</head>
<body>
<script type="text/javascript">
    function del(dno) {
        if (window.confirm("确定删除吗？")) {
            document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + dno
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

    <%
        int i = 0;
        List<Dept> deptList = (List<Dept>) request.getAttribute("depts");
        for (Dept d : deptList) {


    %>

    <tr>
        <td><%=++i%>
        </td>
        <td><%=d.getDeptno()%>
        </td>
        <td><%=d.getDname()%>
        </td>
        <td>
            <a href="javascript:void(0)" onclick="del(<%=d.getDeptno()%>)">删除</a>
            <a href="<%=request.getContextPath()%>/edit.jsp">修改</a>
            <a href="<%=request.getContextPath()%>/detail.jsp">详情</a>
        </td>
    </tr>

    <%
        }
    %>

</table>


</body>
</html>
<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>

