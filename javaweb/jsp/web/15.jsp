<%@page contentType="text/html;charset=UTF-8" %>

<%--
    JSP中EL表达式的隐含对象
    1.pageContext
    2.param
    3.paramValues
    4.initParam
    5.其他
--%>

应用的根路径：${pageContext.request.contextPath}

<%--request是JSP九大内置对象之一--%>
<%--request.getParameter("username") 获取请求的参数--%>
用户名：<%=request.getParameter("username")%>
用户名：${param.username}

<%--假设用户提交的数据是采用checkbox进行提交的。同一组的checkbox的name是一样的--%>
<%--param 获取的是请求参数的一维数组中的第一个元素--%>
爱好：${param.aihao}
爱好：<%=request.getParameter("aihao")%>

一维数组：${paramValues.aihao}
一维数组：<%=request.getParameterValues("aihao")%>

<%--获取数组当中的元素： [下标]--%>
爱好：${paramValues.aihao[0]} ${paramValues.aihao[1]} ${paramValues.aihao[2]}

<%--EL表达式中的隐含对象：initParam--%>
<%--ServletContext是Servlet上下文对象，对应的JSP九大内置对象之一的application--%>
${initParam.pageSize}