package org.ruqinhu.liang.food.context;

import java.util.HashMap;
import java.util.Map;

public class IdentityContext {

    private static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public static final String KEY_STORE_ID ="STORE_ID";

    public static void clear(){
        threadLocal.remove();
    }

    public static String get(String key) {
        Map<String,String> map = threadLocal.get();
        if (map==null) {
            return null;
        }
        return map.get(key);
    }

    public static void set(String key,String value) {
        Map<String,String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(8);
            threadLocal.set(map);
        }
        map.put(key, value);
    }
}
