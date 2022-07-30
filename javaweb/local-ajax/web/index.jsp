<%--
  Created by IntelliJ IDEA.
  User: Song yuhang
  Date: 2022/4/7
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>局部刷新-ajax</title>
    <%--    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.serverPort}${pageContext.request.ContextPath}/"/>--%>
    <script type="text/javascript">
        function doAjax() {

            //使用内存中的异步对象，代替浏览器发起请求。异步对象是用js创建和管理的

            /**
             * ajax请求的代码包括四步，这四步是固定的。
             * AJAX发送请求全靠浏览器内置的这个对象：XMLHttpRequest对象
             * 使用这个XMLHttpRequest对象可以在浏览器当中单独启动一个新的浏览器线程，通过浏览器线程发送该请求，以达到异步效果
             */
                //1.创建异步对象（浏览器内置的，可以直接使用）
            var xmlHttp = new XMLHttpRequest();

            //2.绑定事件/注册回调函数
            //程序执行到这里的时候，后面的回调函数并不会执行，只是将回调函数注册给 xmlHttp 对象
            //等 xmlHttp 对象的readyState发生改变的时候，后面的回调函数会执行
            /*
              XMLHttpRequest对象在请求和响应的过程当中，该对象的readyState状态从0-4
                  0：请求未初始化
                  1：服务器连接已建立
                  2：请求已接收
                  3：请求处理中
                  4：请求已完成，且响应已就绪
             */
            xmlHttp.onreadystatechange = function () {  //该对象的readyState的值发生改变的时候，回调函数会执行
                //处理服务器端返回的数据，更新当前页面
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var data = xmlHttp.responseText;
                    document.getElementById("mydata").innerText = data;
                }
            }

            //3.初始请求数据/开启浏览器和服务器之间的通道
            /*
              method：指定请求方式为get还是post
              url：请求路径
              async：true / false （true表示支持异步，false表示支持同步）
             */
            //获取dom对象的value属性值
            var name = document.getElementById("name").value;
            var w = document.getElementById("w").value;
            var h = document.getElementById("h").value;

            //拼接数据进行发送
            var param = "name=" + name + "&w=" + w + "&h=" + h;
            xmlHttp.open("get", "bmiAjax?" + param, true);

            //4.发起请求
            xmlHttp.send();
        }
    </script>
</head>
<body>
<p>局部刷新ajax-计算bmi</p>
<div>
    <%--没有使用form--%>
    姓名：<input type="text" id="name"><br>
    体重（公斤）：<input type="text" id="w"><br>
    身高（米）：<input type="text" id="h"><br>
    <input type="button" value="提交" onclick="doAjax()">
    <br>
    <br>
    <div id="mydata">等待加载数据。。。</div>
</div>
</body>
</html>
