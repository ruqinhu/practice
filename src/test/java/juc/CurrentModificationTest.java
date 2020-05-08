package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CurrentModificationTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            if ("1".equals(item)) {
//                iterator.remove();
//            }
//        }
        // 1 不抛异常 2 抛异常
        for (String item : list) {
            System.out.println(item);
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
    }

}
