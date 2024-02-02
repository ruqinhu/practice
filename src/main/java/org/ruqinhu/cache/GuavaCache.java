package org.ruqinhu.cache;

import com.google.common.cache.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GuavaCache {

    private static ConcurrentHashMap<String, String> putil = new ConcurrentHashMap<>();

    LoadingCache<String, String> cache = null;

    private void init() {
        cache = CacheBuilder.newBuilder()
                //cache最大容量
                .maximumSize(2)
                //缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变得陈旧不可用，这种回收方式是可取的。
                .expireAfterWrite(1, TimeUnit.MINUTES)
                //声明一个监听器，以便缓存项被移除时做一些额外操作
                .removalListener(new MyListener())
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return putil.get(key);
                    }
                });
    }

    public static void main(String[] args) throws Exception {
        putil.put("app.name", "1");
        putil.put("app.version", "2");
        putil.put("app.port", "3");
        GuavaCache cache = new GuavaCache();
        cache.init();
        cache.testCache();
    }

    private void testCache() {
        try {
            System.out.println("before:" + cache.size());
            String name = cache.get("app.name");
            cache.get("app.version");
            cache.get("app.port");
            System.out.println("after:" + cache.size());
            System.out.println("name :" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyListener implements RemovalListener<String, String> {
        @Override
        public void onRemoval(RemovalNotification<String, String> removalNotification) {
            String key = removalNotification.getKey();
            String value = removalNotification.getValue();
            RemovalCause cause = removalNotification.getCause();
            System.out.println("key:"+ key + ",value:" + value + " removed, cause:" + cause.toString());
        }
    }
}
