package org.ruqinhu.thread.poll.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaorunze
 * @since 2020/1/6
 */
public class NameThreadFactoryTest {

    private static final int THREAD_TOTAL_SIZE = 5;
    private static final long KEEP_ALIVE_TIME =Integer.MAX_VALUE;

    public static void main(String[] args) {
        ExecutorService ThreadPoolTest1 = new ThreadPoolExecutor(THREAD_TOTAL_SIZE,
                THREAD_TOTAL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                new NameThreadFactory("ThreadPoolTest", THREAD_TOTAL_SIZE));
        int i = 0;
        while(i++ < THREAD_TOTAL_SIZE){
            ThreadPoolTest1.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

        ExecutorService ThreadPoolTest2 = new ThreadPoolExecutor(THREAD_TOTAL_SIZE,
                THREAD_TOTAL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                new NameThreadFactory("ThreadPoolTest", THREAD_TOTAL_SIZE));
        int j = 0;
        while(j++ < THREAD_TOTAL_SIZE){
            ThreadPoolTest2.submit(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

}
