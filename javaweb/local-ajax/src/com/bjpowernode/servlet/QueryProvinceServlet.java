package com.bjpowernode.servlet;

import com.bjpowernode.entity.Province;
import com.bjpowernode.entity.QueryDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/qp")
public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //json类型的数据是用{}括起来的，所以空数据也写个花括号
        String json = "{}";
        //调用QueryDao，获取所有的省份信息，是一个list集合
        QueryDao queryDao = new QueryDao();
        List<Province> provinces = queryDao.queryProvinceList();
        //把list转为json格式的数据，输出给ajax请求
        if (provinces != null) {
            //调用jackson工具库，实现List --> json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }

        //输出json数据，响应ajax请求，返回数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();


    }
}
