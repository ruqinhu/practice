package juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class CountDownLatchTest {

    List<String> list = new ArrayList<>(8);

    @Test
    public void testCountDownLatch() throws InterruptedException {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        CountDownLatch c = new CountDownLatch(4);
        new Thread(() -> {
            System.out.println(list.stream().filter(t -> t.equals("1")).collect(Collectors.toList()));
            c.countDown();
        }).start();
        new Thread(() -> {
            System.out.println(list.stream().filter(t -> t.equals("2")).collect(Collectors.toList()));
            c.countDown();
        }).start();
        new Thread(() -> {
            System.out.println(list.stream().filter(t -> t.equals("3")).collect(Collectors.toList()));
            c.countDown();
        }).start();
        new Thread(() -> {
            System.out.println(list.stream().filter(t -> t.equals("4")).collect(Collectors.toList()));
            c.countDown();
        }).start();

        c.await();
        System.out.println("结束");
    }

}
