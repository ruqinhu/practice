package thread.pool.factory;

import org.junit.Test;
import org.ruqinhu.thread.poll.factory.NameThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPollExecutor {

    @Test
    public void testThreadPollExecutor() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 1000,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),new NameThreadFactory("testThreadPollExecutor",5));
        int i = 0;
        while(i++ < 10) {
            executorService.submit(() ->
            {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        Thread.sleep(300000);
    }

}
