package learn.web.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类SimpleDateFormat非线程安全
 * 用类SimpleDateFormat做为共享变量格式化日期会出现多线程问题
 * @date 2017-3-13下午9:34:28
 */
public class DateFormatTest {

	public static void main(String[] args) {
		final String dateStr = "2020-01-01";
		//创建多个线程：对于SimpleDateFormat日期格式化及容易出现多线程问题
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				DateFormatInner.parseDate(dateStr);
			}, String.valueOf(i)).start();
		}
	}
}

class DateFormatInner {
	//定义静态变量让多个线程共享，非线程安全
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 解析日期，如果SimpleDateFormat做为共享变量则在多线程环境下解析的日期
	 * 可能出现不正确的日期格式或者抛出异常的情况。
	 * @date 2017-3-13下午9:38:08
	 * @param dateStr 日期字符串
	 */
	public static void parseDate(String dateStr) {
		try {
			Date d = sdf.parse(dateStr);
			String fmtStr = sdf.format(d);
			if (!dateStr.equals(fmtStr)) {
				System.out.println("日期格式化错误："+Thread.currentThread()+"--将日期"+dateStr+"---》格式化为："+fmtStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

