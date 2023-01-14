package learn.web.thread;

import learn.util.PrintUtil;
import learn.util.test.DebugUtil;

/**
 * 自定义线程
 * @date 2023/1/14 11:27
 */
public class CustomThread extends Thread {

    //构造方法里调用方法Thread.currentThread()和this代表的不一定是同一个线程对象
    public CustomThread(String name) {
        super.setName(name);
        DebugUtil.printWithThread("构造方法中调用Thread.currentThread()"); // main线程
        DebugUtil.printWithThread("构造方法中调用this.getName()=" + this.getName());
        System.out.println(Thread.currentThread() == this); // false，此时Thread.currentThread()代表的是main线程
    }

    @Override
    public void run() {
        DebugUtil.printWithThread("run ------ begin ------");
        //1、this和Thread.currentThread()
        //此时的线程名称才是代码设置的线程名称
        DebugUtil.printWithThread("run方法调用this.getName()="+this.getName());
        System.out.println(this == Thread.currentThread());// true

        DebugUtil.printWithThread("run ------ end ------");
    }
}