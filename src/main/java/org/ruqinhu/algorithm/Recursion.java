package org.ruqinhu.algorithm;

public class Recursion {

    public void recursion(int i) {
        System.out.println(factorial(i));
    }

    private int factorial(int i) {
        if (i == 1) {
            return i;
        }
        return i * factorial(i - 1);
    }

}
