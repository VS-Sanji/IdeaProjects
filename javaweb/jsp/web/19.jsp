<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--var用来指定循环中的变量--%>
<%--begin开始--%>
<%--end结束--%>
<%--step步长--%>
<%--底层会将：i存储到pageContext域中--%>
<c:forEach var="i" begin="1" end="10" step="1">
    <%--所以在这里才能使用EL表达式将其取出，因为EL表达式一定是从某个域中取出数据--%>
    ${i}
</c:forEach>


<%
    //创建List集合
    List<User> usersList = new ArrayList<>();

    User user1 = new User("zhansan", "123", null);
    User user2 = new User("lisi", "123123", null);
    User user3 = new User("wangwu", "123123123", null);

    usersList.add(user1);
    usersList.add(user2);
    usersList.add(user3);

    //将List集合存储到域中
    request.setAttribute("UList", usersList);


%>

<%--var="s"这个s代表的是集合中的每个user对象--%>
<%--varStatus="这个属性代表var的状态对象，这里是一个java对象，这个java对象代表了var的状态"--%>
<%--varStatus="这个名字是随意的"--%>
<%--varStatus这个状态对象有count属性，可以直接使用--%>
<c:forEach items="${UList}" var="s" varStatus="userStatus">
    <%--varStatus的count值从1开始，以1递增，主要是用于编号/序号--%>
    编号：${userStatus.count}, name:${s.username} pwd:${s.password}
</c:forEach>