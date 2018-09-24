package learn.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huangxl
 * @date 2018-09-04 22:09
 */
//@WebServlet(value = "/child", loadOnStartup = 1)
public class ChildServlet extends ServletTest {

    public ChildServlet() {
        System.out.println("--ChildServlet----");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("-----ChildServlet#init()---------");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
