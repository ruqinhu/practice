package reflection.factory;

import java.util.ArrayList;
import java.util.List;

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

}
