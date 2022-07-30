<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%
    //创建user对象
    User user = new User();
    user.setUsername("lisi");

    //存储到request域中
    request.setAttribute("user", user);

%>

<%--使用EL表达式取出，并且输出到浏览器--%>
<%--从域中取出数据--%>
${user}
<br>
<%--取user的username--%>
${user.username}
<br>
<%--取出user的username，注意[]中的name需要添加 双引号 ""--%>
<%--[] 里面的没有添加双引号的话，会将其看作变量。如果是带有双引号“user”，则取找user对象的username属性--%>
${user["username"]}