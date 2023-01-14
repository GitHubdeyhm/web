package learn.web.thread;

import learn.util.test.DebugUtil;

/**
 * 线程Thread类常用方法使用说明
 * @date 2018-05-10 上午10:45
 */
public class ThreadMethodTest {

    public static void main(String[] args) {
        //1、Thread.currentThread()和this关键字在某种情况下是有差异的
//        new CustomThread("customThread").start();

        ThreadMethodTest test = new ThreadMethodTest();
//        test.start();

//        test.interrupt();

//        test.interruptedException_sleep();
//        test.interruptedException_join();

        DebugUtil.printWithThread("-- main end --");
    }

    /**
     * 测试线程的start方法
     * @date 2023/1/14 12:37
     */
    void start() {
        Thread th = new Thread(() -> {
            DebugUtil.printWithThread("-- run begin --");
            for (int i = 0; i <10; i++) {
                DebugUtil.sleepSeconds(2);
            }
            DebugUtil.printWithThread("-- run end --");
        }, "线程A");
        try {
            th.start();
            // start()方法只能调用一次，后续多次调用会抛出IllegalThreadStateException异常。
            th.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 测试线程的中断用法
     * @date 2023/1/14 12:10
     */
    void interrupt() {
        Thread th = new Thread(() -> {
            DebugUtil.printWithThread("-- run begin --");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    DebugUtil.printWithThread("遇到了另一个线程调用了中断标记的方法interrupt()");
                    DebugUtil.printWithThread("1.多次调用isInterrupted()方法不会清除中断标记：" + Thread.currentThread().isInterrupted());
                    DebugUtil.printWithThread("2.多次调用isInterrupted()方法不会清除中断标记：" + Thread.currentThread().isInterrupted());
                    break;
                }
            }
            DebugUtil.printWithThread("-- run end --");
        });
        th.setName("线程A");
        th.start();

        //调用了interrupt()方法并不是立即就终止了线程，只是相当于做了一个中断的标记。
        // 需要配合静态方法interrupted或者实例方法isInterrupted来实现中断线程的功能。
        DebugUtil.printWithThread("main线程准备执行线程A的interrupt()方法");
        th.interrupt();
    }

    /**
     * 测试线程抛出中断异常的场景
     * @date 2023/1/14 12:11
     */
    void interruptedException_sleep() {
        Thread th = new Thread(() -> {
            DebugUtil.printWithThread("-- run begin --");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    DebugUtil.printWithThread("遇到了另一个线程调用了中断标记的方法interrupt()，执行sleep方法会抛出中断异常");
                    try {
                        Thread.sleep(10_0000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            DebugUtil.printWithThread("-- run end --");
        }, "线程A");
        th.start();

        //3、调用了interrupt()方法在线程内使用sleep()/join()/wait()方法会抛出异常InterruptedException
        DebugUtil.printWithThread("main线程准备执行线程A的interrupt()方法");
        th.interrupt();
    }

    void interruptedException_join() {
        Thread th = new Thread(() -> {
            DebugUtil.printWithThread("-- run begin --");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    DebugUtil.printWithThread("遇到了另一个线程调用了中断标记的方法interrupt()，执行join方法会抛出中断异常");
                    try {
                        Thread.currentThread().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            DebugUtil.printWithThread("-- run end --");
        }, "线程A");
        th.start();

        //3、调用了interrupt()方法在当前线程内使用sleep()/join()/wait()方法会抛出异常InterruptedException
        DebugUtil.printWithThread("main线程准备执行线程A的interrupt()方法");
        th.interrupt();

        /*new Thread(() -> {
            DebugUtil.printWithThread("-- run begin --");
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DebugUtil.printWithThread("-- run begin --");
        }, "线程B").start();*/
    }
}

