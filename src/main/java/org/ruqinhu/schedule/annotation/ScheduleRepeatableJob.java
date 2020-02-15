package org.ruqinhu.schedule.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleRepeatableJob {

    /**
     *  任务名称
     */
    String name();

    /**
     * 任务间隔时间，单位 second
     */
    int intervalTime();

    /**
     * 失败重试次数
     * @return
     */
    int retryTime() default 1;

    /**
     * 超时时间，单位 second
     * @return
     */
    int timeoutTime() default 10;
}
