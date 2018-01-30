package Utils;

/**
 * <p>
 *     字符串工具类
 * </p>
 *
 * @author wangbowang
 * @since 2018/01/25
 */
public class StringUtils {

    /**
     * 判断字符串是否为null或空
     */
    public static boolean isEmpty(String str){
        return null==str || "".equals(str.trim());
    }

    /**
     * 判断字符串是否不为null或空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
