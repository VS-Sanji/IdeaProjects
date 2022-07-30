<%@ page import="com.bjpowernode.javaweb.jsp.User" %>
<%@ page import="com.bjpowernode.javaweb.jsp.Address" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%
    //创建user对象
    User user = new User();
    user.setUsername("zhangsan");
    user.setPassword("123123");
    user.setAge("22");

    //创建地址Address对象
    Address address = new Address();
    address.setCity("beijing");
    address.setStreet("chaoyang");
    address.setZipcode("a");

    user.setAddr(address);

    //将User对象存储到request域中
    request.setAttribute("user", user);
%>

<%--使用EL表达式， 从request域中，取出user对象，并将其输出到浏览器--%>
<%--1.EL表达式会自动从某个范围中取数据。2.将其转成字符串 3.将其输出到浏览器--%>
//这个大括号里面的内容和我们在存入域中的 name 一致，但是不加引号，加了引号就会当成普通字符串输出
${user}
<br>
<%--想输出user对象的username属性，可以用 xx. 的方式。（前提是该属性有对应的get方法获取）--%>
${user.username}//相当于<%=request.getAttribute("user").getUsername()%>

<%--输出password--%>
${user.password}

<%--输出年龄--%>
${user.age}

//不能添加双引号，会当作普通字符串
${"user"}

//取出user的住址信息
同样的，city等属性也具有相应的get方法，所以可以直接 .
${user.addr.city}

${user.addr.street}

${user.addr.zipcode}


