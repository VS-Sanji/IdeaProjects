package com.bjpwernode.javaweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * 监听器中的方法不需要程序员手动调用，是发生某个特殊事件之后被服务器调用
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {//服务器启动时间点，想在这个手执行一段代码，就可以考虑写在这个方法中
        //想在这个特殊的时刻执行代码，你写就是了，它会被服务器自动调用
        //这个方法实在ServletContext对象被创建的时候调用
        System.out.println("ServletContext对象被创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {//服务器关闭时间点
        //想在这个特殊的时刻执行代码，你写就是了，它会被服务器自动调用
        //这个方法实在ServletContext对象被销毁的时候调用
        System.out.println("ServletContext对象被销毁了");
    }
}
