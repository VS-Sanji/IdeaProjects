<%@page contentType="text/html;charset=UTF-8" %>

<%
    System.out.println("hello jsp");


%>
<!--HTML的注释，这个注释不专业，仍然会被翻译到java源代码中，在JSP中不要使用这种注释-->

<%--<JSP的专业注释，不会被翻译到java源代码中，建议使用这种方式--%>

<%--方法体中是不能用静态代码块的，也不能方法套方法，不符合语法--%>
<%--
    static {
    System.out.println("");
    }
    public static void m(){
    }
--%>

<%
    //这一对符号，表示其中的内容写到了service方法的方法体中
%>

<%!
    //这一对符号，表示其中的内容写到了service方法的方法体之外，类体之中
    //这种 符号，这种语法，基本不用，因为在service方法外面写静态变量和实例变量，都会存在线程安全问题，
    //因为jsp就是servlet，servlet是单例的，多线程并发的环境下，这个静态变量和实例变量一旦有修改操作，必然会存在线程安全问题
    public static void print() {
        System.out.println("在service之外，类体之中");
    }
%>


<%--在jsp中，向浏览器输出java变量--%>
<%
    String name = "lisi";
    //out是jsp九大内置对象之一
    out.write("name = " + name);
%>

<%--如果输出的内容中，含有java代码，这个时候可以使用以下语法格式--%>
<%=100 + 200%>  <%--    out.print(100 + 200);   --%>












