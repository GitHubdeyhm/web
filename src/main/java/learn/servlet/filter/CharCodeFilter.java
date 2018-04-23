package learn.servlet.filter;

import learn.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 字符编码过滤器
 * @author huangxiaolin
 * @date 2018-04-23 下午4:31
 */
//@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "charset", value = "UTF-8")})
public class CharCodeFilter implements Filter {

    private String charset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset = filterConfig.getInitParameter("charset");
        if (StringUtil.isNullOrEmpty(charset)) {
            charset = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);
        System.out.println("--------调用编码过滤器CharCodeFilter#doFilter()-------");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
