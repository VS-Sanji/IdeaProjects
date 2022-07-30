import jakarta.servlet.ServletException;
import
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/bmiAjax")
public class BmiAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到ajax请求");

        //接收发送过来的数据
        String name = request.getParameter("name");
        String w = request.getParameter("w");
        String h = request.getParameter("h");

        //计算bmi ： bmi = 体重/身高的平方
        Float weight = Float.valueOf(w);
        Float height = Float.valueOf(h);
        float bmi = weight / (height * height);

        //判断bmi的范围
        String msg = "";
        if (bmi <= 18.5) {
            msg = "您比较瘦";
        } else if (bmi > 18.5 && bmi <= 23.9) {
            msg = "您的bmi正常";
        } else if (bmi > 23.9 && bmi <= 27) {
            msg = "您的身体比较胖";
        } else {
            msg = "您的身体肥胖";
        }
        System.out.println("msg=" + msg);
        msg = "您好：" + name + "先生/女士,您的bmi值是：" + bmi + "," + msg;

        //响应ajax需要的数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();

    }
}
