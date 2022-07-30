<%--
  Created by IntelliJ IDEA.
  User: Song yuhang
  Date: 2022/4/11
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省市级联查询</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        //将发送ajax请求的代码封装成一个函数，达到复用的目的
        function loadDataAjax() {
            //做ajax请求，使用jquery的$.ajax(),既能做post请求，又能做get请求
            $.ajax({
                url: "qp",
                dataType: "json",
                success: function (response) {//请求成功后，服务器端返回的数据封装成对象传给处理函数进行处理
                    //可能留存上次查询过的数据，需要清空
                    $("#province").empty();
                    //循环加dom对象
                    $.each(response, function (index, element) {
                        $("#province").append("<option value='" + element.id + "'>" + element.name + "</option>")
                    })
                },
                type: "get"
            })
        }

        $(function () {
            //$(function(){})是在页面的dom对象加载成功之后执行的函数，我们可以让省份信息在此刻直接显示出来，所以可以在此发起ajax请求
            loadDataAjax();

            //绑定事件
            $("#btnLoad").click(function () {
                //做ajax请求，使用jquery的$.ajax(),既能做post请求，又能做get请求

                // $.ajax({
                //     url:"qp",
                //     dataType:"json",
                //     success:function (response){//请求成功后，服务器端返回的数据封装成对象传给处理函数进行处理
                //         //可能留存上次查询过的数据，需要清空
                //         $("#province").empty();
                //         //循环加dom对象
                //         $.each(response,function (index, element){
                //             $("#province").append("<option value='" + element.id + "'>" + element.name + "</option>")
                //         })
                //     },
                //     type:"get"
                // })

                //可以简化，直接调用封装好的函数
                loadDataAjax();
            })

            //当我们所选的省份发生改变时，应该需要更新对应的城市，可以使用 change事件 进行处理
            $("#province").on("change", function () {
                //获取选中的列表框的值
                var obj = $("#province>option:selected");
                var provinceId = obj.val();
                //拿到省份信息，则应该去查询这个省份下的城市信息
                //做ajax请求，使用jquery的$.post()，这种方式中的参数是固定的
                //$.post(url,需要发送给服务器的参数data，处理函数function(response)，数据格式dataType)

                /**
                 * 以下写法中的function可能很长，我们可以在外部定义一个函数来替换他，显得整洁
                 */
                // $.post(
                //     "qc",{"provinceId":provinceId}, function (response){//请求成功后，服务器端返回的数据封装成对象传给处理函数进行处理
                //         //可能留存上次查询过的数据，需要清空
                //         $("#city").empty();
                //         //循环加dom对象
                //         $.each(response,function (index, element){
                //             $("#city").append("<option value='" + element.id + "'>" + element.name + "</option>")
                //         })
                //     },"json"
                // )

                //调用外部处理函数
                $.post("qc", {"provinceId": provinceId}, callback, "json")
            })
        })

        //在这独立定义这个回调函数，然后在$.post()中调用即可
        function callback(response) {//请求成功后，服务器端返回的数据封装成对象传给处理函数进行处理
            //可能留存上次查询过的数据，需要清空
            $("#city").empty();
            //循环加dom对象
            $.each(response, function (index, element) {
                $("#city").append("<option value='" + element.id + "'>" + element.name + "</option>")
            })
        }
    </script>
</head>
<body>
<b>省市级联查询</b>
<br>
<div>
    <table>
        <tr>
            <td>省份：</td>
            <td>
                <select id="province">
                    <option value="0">请选择...</option>
                </select>
            </td>
            <td>
                <input type="button" value="点击查询省份信息" id="btnLoad">
            </td>
        </tr>
        <tr>
            <td>城市：</td>
            <td>
                <select id="city">
                    <option value="0">请选择...</option>
                </select>
            </td>
        </tr>

    </table>
</div>

</body>
</html>
