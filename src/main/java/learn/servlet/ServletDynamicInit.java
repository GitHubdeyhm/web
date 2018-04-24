package learn.servlet;

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
 * 要让实现类生效需要在生成的jar包目录的META-INF目录下新增文件“services/javax.servlet.ServletContainerInitializer”，
 * 文件内容是实现类的全限定类名
 * @author huangxiaolin
 * @date 2018-04-23 下午6:25
 */
@HandlesTypes(ServletTest.class)
public class ServletDynamicInit implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("---servlet动态初始化-------");
        c.forEach(new Consumer<Class<?>>() {
            @Override
            public void accept(Class<?> aClass) {
                System.out.println(aClass);
            }
        });
        //配置一个servlet
        //ServletRegistration.Dynamic servletTest = ctx.addServlet("servletTest", ServletTest.class);
        //servletTest.addMapping("/servlet");
    }
}
