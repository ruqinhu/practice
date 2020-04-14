package org.ruqinhu.algorithm;

public class OutPutAlphabet {

    public static String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static char[] chars = all.toCharArray();

    public static String vowel = "AEIOU";

    public static Integer i = 0;

    public static Boolean end = true;

    public static class A implements Runnable {

        @Override
        public void run() {
                while(end) {
                    boolean t = true;
                    synchronized (i) {
                        while(t) {
                            String iStr = String.valueOf(chars[i]);
                            if (vowel.contains(iStr)) {
                                System.out.println(iStr);
                                i++;
                            } else {
                                t = false;
                            }
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public static class B implements Runnable {

        @Override
        public void run() {
            while (end) {
                boolean t = true;
                synchronized (i) {
                    while(t) {
                        String iStr = String.valueOf(chars[i]);
                        if (!vowel.contains(iStr)) {
                            System.out.println(iStr);
                            i++;
                        } else {
                            t = false;
                        }

                        if ("Z".contains(iStr)) {
                            t = false;
                            end = false;
                        }
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static Boolean outPut() {
//        char[] chars = all.toCharArray();
//        System.out.println(chars[i]);
//        if (vowel.contains(String.valueOf(chars[i]))) {
//            return true;
//        }
//        if ("z".contains(String.valueOf(chars[i]))) {
//            end = false;
//        }
//        return false;
//    }

}
