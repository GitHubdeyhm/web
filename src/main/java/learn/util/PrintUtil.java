package learn.util;

import java.time.LocalDateTime;

/**
 * 打印工具类
 * @date 2022/5/9 21:04
 */
public abstract class PrintUtil {

    /**
     * 控制台打印工具方法，增加当前时间和线程名称输出
     * @date 2022/5/9 21:05
     */
    public static void println(Object message) {
        String msg = LocalDateTime.now() +
                " [" + Thread.currentThread().getName() + "] " +
                (message == null ? "null" : message.toString());
        System.out.println(msg);
    }

    /**
     * 控制台打印工具方法，增加指定参数格式化
     * @date 2022/5/9 21:07
     */
    public static void println(String message, Object... args) {
        String msg = LocalDateTime.now() +
                " [" + Thread.currentThread().getName() + "] " +
                (message == null ? "null" : String.format(message, args));
        System.out.println(msg);
    }
}