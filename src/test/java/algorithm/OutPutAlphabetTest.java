package algorithm;

import org.junit.Test;
import org.ruqinhu.algorithm.OutPutAlphabet;

public class OutPutAlphabetTest {

    @Test
    public void testOutPut() throws InterruptedException {
        Thread t1 = new Thread(new OutPutAlphabet.A());
        Thread t2 = new Thread(new OutPutAlphabet.B());

        t1.start();
        Thread.sleep(300);
        t2.start();

        Thread.sleep(20000);
    }

}
