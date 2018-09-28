package com.lilei.router_compiler.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lilei
 * Date : 2018/9/26
 */

public class CollectionsUtil {
    public static boolean isNotEmpty(List<?> list){
        return list != null && !list.isEmpty();
    }

    public static boolean isNotEmpty(Set<?> set){
        return set != null && !set.isEmpty();
    }

    public static boolean isNotEmptyMap(Map<?, ?> map){
        return map != null && !map.values().isEmpty();
    }
}
