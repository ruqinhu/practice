package org.ruqinhu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *  -XX:HeapDumpPath=D:/code/github/practice/heapdump.dump
 * GC overhead limit exceeded  多次 gc 释放很少空间
 */
public class OutOfMemoryError {

    static class OOMObject {}

    public static void main(String[] args) {
//        List<OOMObject> list = new ArrayList<>();
//        while (true) {
//            list.add(new OOMObject());
//        }
//
        int i = 0;
        List<String> list = new ArrayList<>();
        while (true) {
            list.add(String.valueOf(i++).intern());
        }

    }

}
