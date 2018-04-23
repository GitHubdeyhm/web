package learn.util;

import java.io.*;

/**
 * 流相关工具类
 * @author huangxiaolin
 * @date 2017-08-17 下午2:49
 */
public class IOUtil {

    public static String getStreamContent(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String content = "";
            while ((content = br.readLine()) != null) {
                sb.append(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(br);
        }
        return sb.toString();
    }

    /**
     * 关闭流
     * @author huangxiaolin
     * @date 2018-04-19 15:14
     */
    public static void close(Closeable... closeables) {
        for (Closeable c : closeables)  {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
