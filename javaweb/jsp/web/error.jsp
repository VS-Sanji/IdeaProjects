<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--在错误页面可以启用JSP九大内置对象之：exception--%>
<%--exception内置对象就是刚刚发生的异常对象--%>
<%--需要这行指令才能使用exception对象--%>
<%@page isErrorPage="true" %>

<html>
<head>
    <title>error</title>
</head>
<body>
<h1>程序出错了！！！</h1>

<%--打印异常堆栈信息，输出到后天控制台。exception时九大内置对象之一--%>
<%
    exception.printStackTrace();
%>
</body>
</html>
