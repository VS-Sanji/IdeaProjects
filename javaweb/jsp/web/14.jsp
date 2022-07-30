<%@page contentType="text/html;charset=UTF-8" %>

<%
    //jsp有九大内置对象
    //pageContext request session application response out config exception page
    //其中四个域对象，最小的域是pageContext
    //pageContext翻译为：页面上下文
%>

<%--
    在EL表达式当中没有request这个隐式对象
    requestScope 这个只代表“请求范围”。不等同于request对象
    在EL表达式中只有一个隐式的对象：pageContext
    EL表达式中的pageContext和JSP中的九大内置对象pageContext是同一个对象
--%>
<%--getReqeust()这个方法拿到的是ServletRequest，而它没有getContextPath()方法，需要向下转型--%>
<%=(HttpServletrRequest) pageContext.getRequest().getContextPath()%>
这段java代码对应的EL表达式：
使用EL表达式来获取应用的根
${pageContext.request.contextPath}