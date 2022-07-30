<%@page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
    if(){

    }else if(){

    }else if(){

    }else if(){

    }else if(){

    }
--%>

<%--注意：这个嵌套结构只能这样写，不能随便改--%>
<c:choose>
    <c:when test="${param.age < 18}">
        青少年
    </c:when>

    <c:when test="${param.age < 30}">
        中年
    </c:when>

    <c:when test="${param.age < 50}">
        壮年
    </c:when>

    <c:otherwise>
        老年
    </c:otherwise>
</c:choose>