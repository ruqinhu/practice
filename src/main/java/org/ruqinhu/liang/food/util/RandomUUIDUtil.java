package org.ruqinhu.liang.food.util;

import java.util.UUID;

public class RandomUUIDUtil {

    /**
     * 这里优化为使用 雪花算法生成唯一id
     * @return
     */
    public static String get32RandomStr () {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

}
