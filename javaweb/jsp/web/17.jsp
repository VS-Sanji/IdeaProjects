<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%--简化代码，让jsp中的java代码消失--%>
<%--引入标签库--%>
<%--核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--sql标签库--%>
<%@taglib prefix="" uri="http://java.sun.com/jsp/jstl/sql" %>

<c:forEach></c:forEach>

<c:if test=""></c:if>


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

<%--需求：将List集合中的元素遍历，输出信息到浏览器--%>
<%--使用java代码--%>
<%
    List<User> uList = (List<User>) request.getAttribute("UList");
    //编写for循环遍历list集合
    for (User user : uList) {
%>
name:<%=user.getUsername()%>,pwd:<%=user.getPassword()%>
<%
    }
%>

<%--使用core标签库中的forEach标签。对List集合进行遍历--%>
<%--EL表达式只能从域中取数据--%>
<%--var后面的名字是随意的。var属性代表的是集合中的每一个元素--%>
<c:forEach items="${UList}" var="user">
    name:${user.username} pwd:${user.password}
</c:forEach>



