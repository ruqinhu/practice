package org.ruqinhu.blog;

public class ExceptionTest {


    public static void throwException() {
        throw  new RuntimeException("need some exception");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(ExceptionTest::throwException).start();
        Thread.sleep(4000);
        throwException();
    }

}
