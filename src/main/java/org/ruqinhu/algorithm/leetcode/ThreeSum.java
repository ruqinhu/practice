package org.ruqinhu.algorithm.leetcode;

import java.util.*;

public class ThreeSum {

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> retList = new ArrayList<>();
            Set<Integer> expireSet = new HashSet<>();
            if (nums.length < 3) {
                return retList;
            }
            int len = nums.length;
            for (int i = 0;i < len - 2;i++) {
                for (int j = i + 1;j < len -1;j++) {
                    for (int z = j + 1;z < len;z++) {
                        if (nums[i] + nums[j] + nums[z] == 0) {
                            List<Integer> zeroList = new ArrayList<>();
                            zeroList.add(nums[i]);
                            zeroList.add(nums[j]);
                            zeroList.add(nums[z]);
                            Collections.sort(zeroList);
                            if (!retList.contains(zeroList)) {
                                retList.add(zeroList);
                            }
                        }
                    }
                }
            }
            return retList;
        }

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        Arrays.sort(nums);
        int z = nums.length -1;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j != i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                while(z > j) {
                    if (nums[i] + nums[j] + nums[z] > 0) {
                        z--;
                    } else if (nums[i] + nums[j] + nums[z] == 0) {
                        List<Integer> list = new ArrayList<>(4);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[z]);
                        retList.add(list);
                        z = nums.length - 1;
                        break;
                    } else {
                        z = nums.length - 1;
                        break;
                    }
                }
            }
            z = nums.length - 1;
        }
        return retList;
    }

    public static void main(String[] args) {
        int[] mums = new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        ThreeSum threeSum = new ThreeSum();
        threeSum.threeSum1(mums);
    }
}
