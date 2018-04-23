package learn.util;


/**
 * 字符串工具类
 * @author huangxiaolin
 * @date 2017-12-29 下午5:31
 */
public class StringUtil {

    /** 下划线字符 */
    private static final char UNDERLINE = '_';

    /**
     * 将下划线命名规则转换为驼峰写法，用于将数据库的字段名映射为java字段名。示例：
     * user_name -> userName    password ->password
     * @date 2017-09-03 16:30
     */
    public static String toCamelCaseName(String str) {
        int len = (str == null) ? 0 : str.length();//null或空字符串""
        if (len == 0) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == UNDERLINE) {
                i++;
                if (i < len) {
                    sb.append(Character.toUpperCase(str.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将驼峰命名规则转换为下划线写法，常用于将数据库的字段名映射为java字段名。
     * 首字母始终以小写开头。示例：
     * userName -> user_name    password ->password
     * UserEntity -> user_entity
     * @date 2017-09-03 16:30
     */
    public static String toUnderlineName(String str) {
        int len = (str == null) ? 0 : str.length();//null或空字符串""
        if (len == 0) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                //首字母大写时不添加下划线且转换为小写
                if (i > 0) {
                    sb.append(UNDERLINE);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为null或者去除前后空格后为空
     * @author huangxiaolin
     * @date 2018-01-05 14:11
     * @return 为null或者去除前后空格后为空返回true，否则返回false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }




    public static void main(String[] args) {
        System.out.println(toUnderlineName("userNameAb"));
        System.out.println(toUnderlineName("UserEntity"));

    }
}
