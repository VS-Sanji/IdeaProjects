<%@page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
//忽略整个页面中的EL表达式，false表示不忽略
<%
    request.setAttribute("username", "zhagnsan");

%>

${username}//因为忽略了，所以当成字符串输出了

\${username} 这是局部忽略EL表达式，在前面加一个 反斜杠 \

