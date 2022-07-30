package com.bjpowernode.javaweb.servlet.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class LoginServlet extends GenericServlet {

    //这是继承自GenericServlet适配器的init方法执行结果，表明这个ServletConfig对象是服务器创建好的，然后传给了init方法
    //servletConfig对象，小猫咪创建好的org.apache.catalina.core.StandardWrapperFacade@5c27ab6


    @Override
    public void init() {
        System.out.println("LoginServlet中重写的init方法执行了");
    }

//不可取
//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        super.init(servletConfig);
//    }

    /**
     * 思考这个问题：有可能我们需要在LoginServlet方法中重写init方法，然后我们重写了init方法
     * 这个时候会出现一个问题，那就是我们重写了init方法，在浏览器中请求这个LoginServlet的时候，
     * 小猫咪会创建LoginServlet对象，执行init方法，然而这个时候因为我们重写了init方法，不会去调用
     * 父类GenericServlet中的init方法，即父类的init方法没有执行，那么就会导致拿不到小猫咪创建的
     * ServletConfig对象，这就出现了矛盾。我们可以通过给父类init方法添加final修饰符，保证其不会被重写，进而保证其必然会执行
     * <p>
     * 如果我们确实需要重写init方法，却又无法重写父类init方法（被final修饰）有这样一种解决方案。因为父类的init方法（被final修饰）必然会执行一次
     * 且我们重写init方法又不能重写父类传递ServletConfig对象的init方法（被final修饰），那我们可以在父类中再创建一个init方法，
     * 用来给子类重写，而在原来的init方法的方法体中调用新增的init方法，由于多态的关系，他会去执行子类重写的那个init方法，
     * 巧妙地解决了这个问题
     */


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("登录服务正在启动");

        //想要在子类LoginServlet的service方法中使用ServletConfig对象怎么办？
        ServletConfig config = this.getServletConfig();
        System.out.println("service方法中可以获取到ServletConfig对象，因为在其继承的GenericServlet抽象类中，" +
                "通过成员变量的方式，将小猫咪创建的ServletConfig对象从init方法中取了出来，并且通过修改getServletConfig()方法中的返回值，将这个ServletConfig对象传递给子类使用");
    }
}
