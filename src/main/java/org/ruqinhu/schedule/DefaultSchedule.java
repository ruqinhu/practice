package org.ruqinhu.schedule;

import org.ruqinhu.schedule.annotation.ScheduleRepeatableJob;
import org.ruqinhu.schedule.model.DefaultJob;
import org.ruqinhu.schedule.util.ClassUtil;
import org.ruqinhu.thread.poll.factory.NameThreadFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

public class DefaultSchedule implements Runnable{

    private static ArrayList<Class<?>> SCHEDULE_CLASS = new ArrayList<>();

    private static ArrayList<DefaultJob> SCHEDULE_JOB = new ArrayList<>();

    private static ArrayBlockingQueue<DefaultJob> EXECUTE_JOB = new ArrayBlockingQueue<>(8);

    public void run(String pack) {
        //1.获取需要被执行的任务类
        putScheduleClass(pack);

        //2.从任务类中解析出需要执行的任务
        SCHEDULE_JOB.addAll(getJobs(SCHEDULE_CLASS));

        //3.扫描是否到任务时间
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new NameThreadFactory("扫描须执行的任务", true));
        scheduledExecutorService.scheduleAtFixedRate(new DefaultSchedule(), 3, 1, TimeUnit.SECONDS);

        //4.通知任务执行(这里是单机，如果是分布式，则需要记录项目名称,然后可以通过mq调用)
        Executors.newSingleThreadExecutor().execute(this::executeJob);
    }

    /**
     * 1.可以通过自定义注解（在自定义注解上加上 @Component）让spring管理bean
     * 2.可以扫描指定包路径下的类
     * 这里使用第二种
     */
    private void putScheduleClass(String pack) {
        SCHEDULE_CLASS.addAll(ClassUtil.getClasses(pack));
    }

    private List<DefaultJob> getJobs(List<Class<?>> classes) {
        List<DefaultJob> defaultJobs = new ArrayList<>();

        for (Class<?> clazz:classes) {
            Method[] methods = clazz.getMethods();
            for (Method method:methods) {
                if (method.isAnnotationPresent(ScheduleRepeatableJob.class)) {
                    ScheduleRepeatableJob jobAnnotation = method.getAnnotation(ScheduleRepeatableJob.class);
                    defaultJobs.add(getDefaultJob(jobAnnotation, method));
                }
            }
        }
        return defaultJobs;
    }

    private DefaultJob getDefaultJob(ScheduleRepeatableJob jobAnnotation, Method method) {
        DefaultJob defaultJob = new DefaultJob();
        defaultJob.setClazz(method.getDeclaringClass());
        defaultJob.setMethod(method);
        defaultJob.setIntervalTime(jobAnnotation.intervalTime());
        defaultJob.setRetryTime(jobAnnotation.retryTime());
        defaultJob.setTimeoutTime(jobAnnotation.timeoutTime());
        defaultJob.setName(jobAnnotation.name());
        return defaultJob;
    }

    @Override
    public void run() {
        System.out.println("步骤三执行------------------------------------------------------------------------");
        Iterator<DefaultJob> ite = SCHEDULE_JOB.iterator();
        while (ite.hasNext()) {
            DefaultJob defaultJob = ite.next();
            if (defaultJob.getNextExecuteTime() == null
                    || defaultJob.getNextExecuteTime().before(new Date())) {
                //这里应该拷贝一份放到待执行队列中 @TODO
                EXECUTE_JOB.add(defaultJob);
                //设置下次执行时间
                if (defaultJob.getNextExecuteTime() == null) {
                    defaultJob.setNextExecuteTime(new Date(System.currentTimeMillis() + defaultJob.getIntervalTime()*1000));
                }else {
                    defaultJob.setNextExecuteTime(new Date(defaultJob.getNextExecuteTime().getTime() + defaultJob.getIntervalTime()*1000));
                }
            }
        }
    }

    private void executeJob() {
        while(true) {
            System.out.println("步骤四执行------------------------------------------------------------------------");

            try {
                DefaultJob defaultJob = EXECUTE_JOB.poll();
                if (defaultJob != null) {
                    System.out.println(new Date().toLocaleString() + "   执行定时任务任务，任务名称：" + defaultJob.getName());
                    defaultJob.getMethod().invoke(defaultJob.getClazz().newInstance());
                }

                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
