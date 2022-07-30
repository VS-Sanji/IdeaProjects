package com.bjpowernode.javaweb.servlet.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * 与上述简例相同，我们也可以编写一个标准通用的Servlet，起名：GenenricServlet
 * 以后所有的Servlet类都不要直接实现Servlet接口了
 * 以后所有的Serblet类都要继承GenenricServlet类
 * GenericServlet 就是一个适配器
 * GenericServlet实现了Servlet接口，将最主要的，常用的方法设成抽象方法，是一个适配器，以后所有Serblet类继承GenericServlet，重写service方法
 */
public abstract class GenericServlet implements Servlet {

    //成员变量
    private ServletConfig config;

    /**
     * init方法中的ServletConfig对象是小猫咪创建好的
     * 这个ServletConfig对象目前在init方法的参数上，属于局部变量
     * 那么ServletConfig对象肯定以后要在service方法中使用，那么怎么才能保证ServletConfig对象在service方法中能够使用呢？
     * 可以用引入成员变量的方式拿到这个对象
     */


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servletConfig对象，小猫咪创建好的" + servletConfig);
        this.config = servletConfig;
        //调用添加地init方法，方便于子类所重写地init方法地执行（多态）
        this.init();
    }


    //新添加init方法，给需要重写init方法的子类重写

    /**
     * 这个init方法是供子类重写的
     */
    public void init() {

    }

    //将这个getServletConfig()方法的返回值由null改成设置的成员变量，这样子类在继承这个适配器时候，就可以通过这个方法拿到由小猫咪创建好的servletConfig对象
    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    /**
     * 抽象方法，这个方法最常用。所以要求子类必须实现service方法
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
