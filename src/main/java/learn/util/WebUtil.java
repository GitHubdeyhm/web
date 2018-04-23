package learn.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * web项目工具类
 * @author huangxiaolin
 * @date 2018-01-24 下午3:20
 */
public class WebUtil {

    /**
     * js跨域资源共享
     * @author huangxiaolin
     * @date 2018-01-24 15:21
     */
    public static void corsOrigin(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        if (StringUtil.isNullOrEmpty(origin)) {
            response.setHeader("Access-Control-Allow-Origin",
                    "http://127.0.0.1:8020,http://cltest.h5.rongshutong.com");
        } else {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        //设置一些响应头，多个以英文逗号分隔。对于自定义的响应头在跨域的时候js可能存在获取不到的情况
        response.setHeader("Access-Control-Expose-Headers", "x-auth-token");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * 获取所有请求头信息
     * @author huangxiaolin
     * @date 2018-04-23 16:07
     */
    public static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        System.out.println("------请求头begin-------------");
        Map<String, String> headMap = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            headMap.put(header, request.getHeader(header));
            System.out.println(header+"="+request.getHeader(header));
        }
        System.out.println("------请求头end-------------");
        return headMap;
    }

    /**
     * 判断上传文件的类型是否是允许上传的类型
     * @author huangxiaolin
     * @date 2018-04-19 13:50
     * @return 允许的文件类型返回true，否则返回false
     */
    public static boolean isAllowFileType(String fileName, String... fileTypes) {
        if (StringUtil.isNullOrEmpty(fileName)) {
            return false;
        }
        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return false;//该文件没有扩展名
        }
        String extName = fileName.substring(index + 1).toLowerCase();//确保扩展名小写
        for (String type : fileTypes) {
            if (type.equals(extName)) {
                return true;
            }
        }
        return false;
    }
}
