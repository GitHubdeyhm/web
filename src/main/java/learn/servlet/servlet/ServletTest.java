package learn.servlet.servlet;

import learn.util.IOUtil;
import learn.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * servlet测试。属性说明：
 * loadOnStartup=1代表程序启动时就初始化servlet，否则在调用servlet时才会初始化servlet。
 * @author huangxl
 * @date 2017-09-17 22:01
 */
@WebServlet(urlPatterns = "/servlet", loadOnStartup = 1)
public class ServletTest extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("======init()====");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        System.out.println("-------进入了方法ServletTest#doGet()--------");

        PrintWriter writer = response.getWriter();
        PrintWriter writer2 = response.getWriter();
        System.out.println(writer == writer2);//true，多次调用getWriter()方法返回的是同一个对象

        //获取请求参数
        String username = request.getParameter("username");
        writer.write("响应完成："+username);//直接响应，不跳转页面
    }

    //处理post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        WebUtil.getRequestHeaders(req);

        System.out.println(req.getParameter("username"));//null
        /**
         * 获取请求的json数据，此时请求头content-type=application/json。说明：
         * 1、json请求不能通过req.getParameter("username")方法获取请求的数据。
         * 2、在整个请求HttpServletRequest的范围中，req.getReader()/req.getInputStream();最多只能读取一次，
         * 不能重复读取。重复读取会抛出异常"java.io.IOException: Stream closed"
         * @author huangxiaolin
         * @date 2018-04-23 16:21
         */
        BufferedReader br = req.getReader();
        StringBuilder param = new StringBuilder();
        try {
            String content = "";
            while ((content = br.readLine()) != null) {
                param.append(content);
            }
            System.out.println("请求参数："+param.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(br);
        }

        /*BufferedReader br2 = req.getReader();
        StringBuilder param2 = new StringBuilder();
        try {
            String content = "";
            while ((content = br.readLine()) != null) {
                param.append(content);
            }
            System.out.println("请求参数："+param.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(br2);
        }*/

        resp.getWriter().write("json");
    }
}
