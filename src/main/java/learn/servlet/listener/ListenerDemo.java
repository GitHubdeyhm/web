package learn.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 2018/9/11 0:20
 */
//@WebListener
public class ListenerDemo implements ServletContextListener {

    public ListenerDemo() {
        System.out.println("-ListenerDemo------");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("---ListenerDemo#contextInitialized()---");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
