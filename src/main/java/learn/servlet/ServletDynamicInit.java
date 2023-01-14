package learn.servlet;

import learn.servlet.listener.ListenerDemo;
import learn.servlet.servlet.ServletTest;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;
import java.util.function.Consumer;

/**
 * servlet通过实现ServletContainerInitializer接口可以动态注册servlet。
 * 此时相应的servlet实现类不需要通过注解标注，一般用于三方jar包初始化servlet。
 * 要让实现类生效需要在生成的jar包目录的META-INF目录下新增名称为“services/javax.servlet.ServletContainerInitializer”，
 * 的文件且文件内容是实现类的全限定类名。
 * @author huangxiaolin
 * @date 2018-04-23 下午6:25
 */
@HandlesTypes(ListenerDemo.class)
public class ServletDynamicInit implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext ctx) throws ServletException {
        System.out.println("---servlet动态初始化-------");
        if (set != null) {
            for (Class<?> clazz : set) {
                System.out.println(clazz);
                ctx.addListener(clazz.getName());
            }
        }
//        ctx.addListener("learn.servlet.listener.ListenerDemo");
        ctx.addListener(new ListenerDemo());
        //配置一个servlet
//        ServletRegistration.Dynamic servletTest = ctx.addServlet("servletTest", ServletTest.class);
//        servletTest.addMapping("/servlet");
    }
}
