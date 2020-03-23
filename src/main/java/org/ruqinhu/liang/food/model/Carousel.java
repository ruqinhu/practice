package org.ruqinhu.liang.food.model;

import lombok.Data;

import java.util.Date;

/**
 * 轮播表可以分为两个表：默认轮播表，指定门店轮播表
 * interval 有两种设计方式，示例给的是时段，还可以列出所有的执行时+分隔符 8#,9#,10#，这样可以在数据库中直接like
 */
@Data
public class Carousel {

    private String content;

    private Date startDate;

    private Date endDate;

    /**
     * 示例：8-9,9-10
     * 逗号分隔的时段
     */
    private String interval;

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
