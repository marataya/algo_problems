package leetcode.p_1__two_sum;

import java.util.HashMap;

public class Solution_2 {
    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[0];
    }
}
