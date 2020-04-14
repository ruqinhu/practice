package org.ruqinhu.algorithm;

public class DeadLock {

    static class A {
        static Object a = new Object();
    }

    static class B {
        static Object b = new Object();
    }

    public static void main(String[] args) throws InterruptedException {
//        A a = new A();
//        B b = new B();

        Thread t1 = new Thread(() -> {
            synchronized (A.a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(B.b) {
                    System.out.println(B.b);
                }
            }
            System.out.println("释放了锁");
        });

        Thread t2 = new Thread(() -> {
            synchronized (B.b) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A.a) {
                    System.out.println(A.a);
                }
            }
            System.out.println("释放了锁");
        });

        t1.start();
        t2.start();

        Thread.sleep(10000);
    }

}
