package schedule;

import org.junit.Test;
import org.ruqinhu.schedule.DefaultSchedule;

public class DefaultScheduleTest {

    @Test
    public void testRun() throws InterruptedException {
        DefaultSchedule defaultSchedule = new DefaultSchedule();
        defaultSchedule.run(this.getClass().getPackage().getName());

        Thread.sleep(100000);
    }

}
