<%@ page import="java.io.ObjectOutputStream" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%--
    关于EL表达式中的运算符号
        1.算术运算符
            + - * / %
        2.关系运算符
            == != > >= < <= eq
        3.逻辑运算符
            ! && || not and or (注意：！和 not 都是取反）
        4.条件运算符
            ? :
        5.取值运算符
            [] 和 .
        6.empty 运算符

--%>

${10 + 20}
<%--在EL表达式中“+”运算符只会做求和运算，不会进行字符串拼接操作,+号两边不是数字的时候会转成数字，转不成就报错--%>
<%--“20”会被自动转换成数字20--%>
${10 + "20"}

<%--关系运算符: == eq != 都是调用了equals方法--%>
${"1" == "1"}//true

<%
    Object o = new Object();
    request.setAttribute("1", o);
    request.setAttribute("2", o);
%>
${1 == 2}//true


<%--一下语法错误，没有加小括号--%>
${!1 eq 2}
<%--正确的--%>
${!(1 eq 2)}

<%--empty运算符--%>
<%--判断是否为空，如果为空，结果是true，不为空，结果是false--%>
${empty param.username}

<%--结果为false，因为empty运算符的结果是 boolean类型，只有两个值，与null无法比较，用==判断为false--%>
${empty param.password == null}

