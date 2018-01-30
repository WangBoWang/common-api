package Utils;

import java.util.Collection;

/**
 * <p>
 *     集合工具类
 * </p>
 *
 * @author wangbowang
 * @since 2018/01/25
 */
public class CollectionUtils {

    /**
     *判断集合是否为空
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 判断集合是否为非空
     */
    public static boolean isNotEmpty(Collection coll) { return !isEmpty(coll);}



}
