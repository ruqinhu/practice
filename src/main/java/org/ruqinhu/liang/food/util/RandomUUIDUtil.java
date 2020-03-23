package org.ruqinhu.liang.food.util;

import java.util.UUID;

public class RandomUUIDUtil {

    public static String get32RandomStr () {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

}
