package learn.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 2018/9/10 23:07
 */
//@WebFilter(urlPatterns = "/servlet")
public class FilterDemo implements Filter {

    public FilterDemo() {
        System.out.println("----Filter---");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----FilterDemo#init()----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("----进入FilterDemo#doFilter()----");
        chain.doFilter(request, response);
        System.out.println("-----wwwwwww");
    }

    @Override
    public void destroy() {

    }
}
