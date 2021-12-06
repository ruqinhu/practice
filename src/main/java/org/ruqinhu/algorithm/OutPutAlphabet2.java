package org.ruqinhu.algorithm;

import java.util.concurrent.locks.ReentrantLock;

public class OutPutAlphabet2 {

    private static String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static ReentrantLock lock = new ReentrantLock();

    private static Boolean isVowel(CharSequence c) {
        return "aeiou".contains(c);
    }

    private static class Vowel implements Runnable{
        @Override
        public void run() {
            for (int i = 0;i < letters.length; i++) {
                lock.lock();
                if (isVowel(letters[i])) {
                    System.out.println(letters[i]);
                }
                lock.unlock();
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    private static class Constant implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i < letters.length; i++) {
                lock.lock();
                if (!isVowel(letters[i])) {
                    System.out.println(letters[i]);
                }
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

        Thread cowel = new Thread(new Vowel());
        Thread constant = new Thread(new Constant());

        cowel.start();
        constant.start();

        Thread.sleep(4000000);

    }

}
