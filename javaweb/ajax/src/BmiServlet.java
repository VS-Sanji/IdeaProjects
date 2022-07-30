import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/bs", "/bp"})
public class BmiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收请求参数
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

//        //将结果存到request域中
//        request.setAttribute("msg", msg);
//
//        //转发到结果页面
//        request.getRequestDispatcher("/result.jsp").forward(request,response);

        //采用直接响应的方式
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }
}
