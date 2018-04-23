package learn.servlet.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 声明一个异步servlet。说明：
 * 1、这里异步的意思不是马上响应客户端，而是将一个客户端线程返回给web容器。这样可以提供并发量。
 * 2、异步servlet如果有过滤器Filter的话则该Filter也应该是异步的。
 * 3、异步servlet一般用于业务处理较耗时的，这主要体现在文件上传操作，以及其它的跨网络调用等
 * @author huangxl
 * @date 2017-09-17 23:02
 */
@WebServlet(urlPatterns = "/servlet/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取异步上下文
        //System.out.println(req.isAsyncStarted()+"--"+req.isAsyncSupported());
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        //启动异步请求
        AsyncContext asyncContext = req.startAsync();
        //设置异步处理的超时时间，默认为30s
        asyncContext.setTimeout(10000);
        //异步事件监听器
        asyncContext.addListener(new MyAsyncListener());
        //异步提交的线程
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步提交的线程");
                try {
                    Thread.sleep(5000);
                    //响应客户端
                    asyncContext.getResponse().getWriter().write("异步servlet");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                //应该显示调用完成异步请求，否则会一直等待异步超时时间
                asyncContext.complete();
            }
        });
        resp.getWriter().write("kkkkkkkkkkkkk");
    }
}
/**
 * 除此之外，Servlet 3.0 还为异步处理提供了一个监听器，使用 AsyncListener 接口表示。它可以监控如下四种事件：
    异步线程开始时，调用 AsyncListener 的 onStartAsync(AsyncEvent event) 方法；
    异步线程出错时，调用 AsyncListener 的 onError(AsyncEvent event) 方法；
    异步线程执行超时，则调用 AsyncListener 的 onTimeout(AsyncEvent event) 方法；
    异步执行完毕时，调用 AsyncListener 的 onComplete(AsyncEvent event) 方法；
*/
class MyAsyncListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("------onComplete------");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("------onTimeout------");

    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("------onError------");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("------onStartAsync------");
    }
}
