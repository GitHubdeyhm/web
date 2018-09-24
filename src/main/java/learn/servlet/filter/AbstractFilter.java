package learn.servlet.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 抽象类过滤器
 * 2018/9/10 23:11
 */
public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) request;
        doFilter(httpServletRequest, httpServletResponse, chain);

    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain);

    @Override
    public void destroy() {

    }
}
