package reflection.factory;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public List<Integer> Calculation(List<Integer> ss, List<Integer> p1, List<Integer> p2) {
        List<Integer> ret = new ArrayList<>();
        for (Integer s:ss) {
            int i = 0;
            while(i<p1.size() || i<p2.size()) {
                if (p1.get(i) + p1.get(i) == s) {
                    ret.add(i);
                    break;
                }
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
            reentrantLock.lock();
            condition.signal();
            reentrantLock.unlock();
            System.out.println(3);
        });

        thread.start();

        System.out.println(1);
        reentrantLock.lock();
        condition.await();
        reentrantLock.unlock();
        System.out.println(4);


    }

}
