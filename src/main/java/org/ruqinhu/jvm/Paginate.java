package org.ruqinhu.jvm;

import java.util.ArrayList;
import java.util.List;

public class Paginate {

    public static List<Integer> paginate(int totalItems, int pageSize, int page) {
        List<Integer> dataRange = new ArrayList<>();

        // 计算总页数
        int totalPages = (totalItems + pageSize - 1) / pageSize;

        // 检查请求的页数是否在有效范围内
        if (page < 1 || page > totalPages) {
            return dataRange; // 返回空列表表示页数超出范围
        }

        // 计算起始索引和结束索引
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(page * pageSize, totalItems);

        // 生成数据范围
        for (int i = startIndex; i < endIndex; i++) {
            dataRange.add(i);
        }

        return dataRange;
    }

    public static void main(String[] args) {
        // 示例用法
        int totalItems = 15;
        int pageSize = 8;
        int page = 3;
        List<Integer> result = paginate(totalItems, pageSize, page);
        System.out.println(result);
    }

}
