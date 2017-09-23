package learn.servlet.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author huangxl
 * @date 2017-09-17 22:24
 */
@WebFilter(urlPatterns = "/servlet")//“/*”代表拦截所有的请求，包括静态资源文件
public class FilterTest implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----进入了FilterTest#init()方法----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("----进入了FilterTest#doFilter()方法----");
    }

    @Override
    public void destroy() {
        System.out.println("----进入了FilterTest#destroy()方法----");
    }
}
