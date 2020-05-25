package org.ruqinhu.algorithm.leetcode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 */

public class ContainerWithMostWater {

    /**
     * fMax 是容器中容纳水最多的
     * aMax 是单次循环中容纳水最多的
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int fMax = 0;
        for(int i = 0;i < height.length;i++) {
            int aMax = 0;
            for (int j = 0;j <= i;j++) {
                int jMax = height[i] < height[j] ? height[i]*(i-j) : height[j]*(i-j);
                aMax = Math.max(aMax, jMax);
            }
            fMax = Math.max(fMax, aMax);
        }
        return fMax;
    }

}
