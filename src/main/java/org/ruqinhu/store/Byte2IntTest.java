package org.ruqinhu.store;

public class Byte2IntTest {

    public static void main(String[] args) {
        //  最大值转 byte 会丢失长度
        int max = Integer.MAX_VALUE;
        byte intMax = (byte) max;
        System.out.println(intMax);
        System.out.println(intMax & 0xFF);
    }

}
