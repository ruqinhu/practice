package org.ruqinhu.schedule.model;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Date;

@Data
public class DefaultJob {

    private String name;

    private Integer intervalTime;

    private Integer retryTime;

    private Integer timeoutTime;

    /**
     * 分布式下项目名称
     */
    private String applicationName;

    private Class<?> clazz;

    private Method method;

    private Date nextExecuteTime;
}
