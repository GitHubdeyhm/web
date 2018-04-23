package learn.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 此类通过servlet3.0+来实现文件上传。
 * 1、通过注解WebServlet定义一个servlet，可以不通过xml文件配置声明了。
 * 2、MultipartConfig注解配置文件上传的servlet一些属性，该注解属性说明：
 *     location：绝对路径，应该是个目录。不支持相对于应用上下文的路径
 *     fileSizeThreshold：超过这个文件的大小的其余部分会被临时保存到磁盘上，默认为0字节，单位为字节
 *     maxFileSize：上传文件的最大值，如果超出了这个最大值容器会抛出IllegalStateException异常，默认为-1代表无限制
 *     maxRequestSize：对于一个multipart/form-data请求，所允许的最大值。如果所有上传文件域的总大小超过了这个值则抛出异常，
 *      默认值为-1代表无限制。
 * 也可以在web.xml文件中配置以上属性，元素名称为<multipart-config></multipart-config>
 * @author huangxl
 * @date 2017-09-17 21:22
 */
@WebServlet(urlPatterns="/servlet/upload")//注解方式定义servlet，指定请求url
@MultipartConfig(location = "/home/jiaoweiwei/Desktop", maxFileSize = 10485760L)//文件上传需配置的注解---指定上传文件的最大值
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//设置字符集
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("username");//获取普通input标签的参数
        System.out.println("普通参数值："+param);

        Part part = request.getPart("file");//获取文件上传域，一个part对象对应一个文件上传域
        System.out.println(part.getContentType()+"----"+part.getName()+"---"+part.getSubmittedFileName());
        //保存文件，路径为注解MultipartConfig的loaction属性指定的目录
        part.write(part.getSubmittedFileName());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
