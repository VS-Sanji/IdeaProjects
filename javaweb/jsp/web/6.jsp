<%@page contentType="text/html;charset=UTF-8" %>

<%
    //向request域中存储username为张三
    request.setAttribute("username", "zhangsan");
    request.setAttribute("obj", new Object());

%>

<%--将request域中的数据取出来，并且输出到浏览器--%>
<%=request.getAttribute("username")%>
<br>
<%=request.getAttribute("obj")%>
<br>

<hr>

<%--使用EL表达式呢？--%>
${username}
<br>
${obj}