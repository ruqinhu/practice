package org.ruqinhu.liang.food.util;

public class KeyValueUtil {

    /**
     * 缓存数据
     * @param key 键
     * @param value 值
     * @param timeSec 时间(秒)
     * @return
     */
    public static boolean set(String key, Object value, long timeSec) {
        try{
            //1.缓存数据

            //2.设置数据过期时间

            //3.返回 ture
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            //3.1 返回 false
            return false;
        }
    }

    /**
     * 根据键获取值
     * @param key 键
     * @param tClass 实体类型
     * @param <T>
     * @return
     */
    public static <T> T get(String key,Class<T> tClass){
        try {
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
