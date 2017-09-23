package learn.servlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 异步servlet
 * @author huangxl
 * @date 2017-09-17 23:02
 */
@WebServlet(asyncSupported = true, urlPatterns = "/async.do")
public class AsyncServlet extends HttpServlet {
}
