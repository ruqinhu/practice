package schedule;

import org.ruqinhu.schedule.annotation.ScheduleRepeatableJob;

public class SoutSchedule {

    @ScheduleRepeatableJob(name = "简单的定时任务", intervalTime = 10)
    public void task() {
        System.out.println("平安健康");
    }

}
