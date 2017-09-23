package learn.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * servlet测试
 * @author huangxl
 * @date 2017-09-17 22:01
 */
@WebServlet("/servlet")
public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("-------进入了方法ServletTest#doGet()--------");

        response.setHeader("response-header", "response-value");

        PrintWriter writer = response.getWriter();
        PrintWriter writer2 = response.getWriter();
        System.out.println(writer == writer2);//true

        writer.write("响应完成！");
        response.setHeader("response-header2", "response-value2");

//        super.doGet(request, response);
    }
}
