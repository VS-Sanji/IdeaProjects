<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%
    Map<String, String> map = new HashMap<>();
    map.put("username", "zhangli");
    map.put("password", "123");

    //将Map集合存储到request域中
    request.setAttribute("userMap", map);

    Map<String, User> userMap2 = new HashMap<>();
    User user = new User();
    user.setUsername("zhangli");
    userMap2.put("user", user);
    request.setAttribute("sdfsd", userMap2);

%>

<%--使用EL表达式将Map集合中的user对象中的username属性取出--%>
${sdfsd.user.username}

<br>

<%--使用EL表达式，将map集合中的数据取出--%>

${userMap.username}
<br>
${userMap.password}
<br>
${userMap["username"]}
<br>
${userMap["password"]}