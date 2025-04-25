package leetcode.p_1__two_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_Bottomup_Test {

    @Test
    void twoSum() {
        Solution_1 solution1 = new Solution_1();
        Solution_2 solution2 = new Solution_2();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        int[] result1 = solution1.twoSum(nums, target);
        int[] result2 = solution2.twoSum(nums, target);
        assertArrayEquals(expected, result1);
        assertArrayEquals(expected, result2);
    }
}
