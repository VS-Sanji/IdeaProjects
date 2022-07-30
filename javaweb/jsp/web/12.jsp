<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%
    //数组对象
    String[] username = {"zhangsan", "lisi", "wangwu"};

    //向域中存储数组
    request.setAttribute("nameArray", username);

    User u1 = new User();
    u1.setUsername("zhangsang");

    User u2 = new User();
    u2.setUsername("lisi");

    User[] users = {u1, u2};

    request.setAttribute("userArray", users);

    ArrayList<String> list = new ArrayList<>();
    list.add("abc");
    list.add("def");

    request.setAttribute("list", list);

    Set<String> set = new HashSet<>();
    set.add("a");
    set.add("b");
    request.setAttribute("set", set);
%>

<%--取出set集合--%>
set集合：${set}

<%--取出list集合--%>
<%--list集合也是通过下标的方式取数据的--%>
${list[0]}
<br>
${list[1]}


<%--使用EL表达式从数组中取元素--%>
${nameArra[0]}
<br>
${nameArray[1]}
<br>
${nameArray[2]}
<br>

<%--取不出数据，在浏览器上直接输出空白，不会出现数组下标越界--%>
${nameArray[111]}