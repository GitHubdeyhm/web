package learn.util.test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 仅用于调试程序相关工具类，不用于实际业务代码
 * @author huangxl
 * @date 2019/3/5 10:23
 **/
public class DebugUtil {

    /**
     * 控制台打印包括当前线程信息
     * @date 2023/1/14 11:24
     */
    public static void printWithThread(Object obj) {
        String message = LocalDateTime.now()
                + " 当前执行线程[id=" + Thread.currentThread().getId() + "] "
                + Thread.currentThread()
                + " | " + obj.toString();
        System.out.println(message);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休眠，以秒为单位
     * @param seconds 休眠的秒数
     * @author huangxl
     * @date 2019/3/5 15:02
     **/
    public static void sleepSeconds(int seconds) {
        sleep(1000L * seconds);
    }

    /**
     * 获取不大于指定字节大小的最接近数据
     * @date 2020/3/1 21:09
     **/
    public static String getMaxData(int maxByteCount) {
        String content = "@@getMaxData-123456这是模拟的数据ABCDEFG--abcdefg_";
        int byteCount = content.getBytes(StandardCharsets.UTF_8).length;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (count + byteCount <= maxByteCount) {
            sb.append(content);
            count = sb.toString().getBytes(StandardCharsets.UTF_8).length;
        }
        return sb.toString();
    }

    /**
     * 获取大于指定字节大小的最接近的数据
     * @date 2020/6/20 18:44
     */
    public static String getMinData(int minByteCount) {
        String content = "@@getMinData-123456这是模拟的数据ABCDEFG--abcdefg_";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (count <= minByteCount) {
            sb.append(content);
            count = sb.toString().getBytes(StandardCharsets.UTF_8).length;
        }
        return sb.toString();
    }

    /**
     * 随机生成一个时间
     * @date 2020/9/22 22:14
     */
    public static LocalDate randomTime() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int year = random.nextInt(2000, Year.now().getValue() + 1);
        int month = random.nextInt(1, 13);
        int day = random.nextInt(1, YearMonth.of(year, month).lengthOfMonth() + 1);
        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args) {
        String data = DebugUtil.getMaxData(1024);
    }
}
