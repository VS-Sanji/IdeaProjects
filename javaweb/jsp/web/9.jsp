<%@page contentType="text/html;charset=UTF-8" %>

<%
    request.setAttribute("username", "zhangsan");

%>

<%--取出数据并且输出到浏览器--%>
<%=request.getAttribute("username")%>
${username}

<br>
<%=request.getAttribute("usernam")%>这里的name没有写对，输出null
${usernam} 这里的name 也没有写对，但是在浏览器上什么也不显示，对null做了处理，如果是null，则在浏览器上输出空白