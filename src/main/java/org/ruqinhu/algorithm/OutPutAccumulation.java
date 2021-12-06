package org.ruqinhu.algorithm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 3 个线程 累加 1 - 100  结果是 5050
 */
public class OutPutAccumulation {

    private static Integer i = 0;

    private static Integer j = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    private static class Runnable1 implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (i == 5050) return;
                i = i + j++;
                System.out.println(i);
                lock.unlock();
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    private static class Runnable2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (i == 5050) return;
                i = i + j++;
                System.out.println(i);
                lock.unlock();
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    private static class Runnable3 implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (i == 5050) return;
                i = i + j++;
                System.out.println(i);
                lock.unlock();
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable1());
        thread1.start();
        Thread thread2 = new Thread(new Runnable2());
        thread2.start();
        Thread thread3 = new Thread(new Runnable3());
        thread3.start();
        Thread.sleep(200000);
    }

}
