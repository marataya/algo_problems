package leetcode.problem_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_1Test {

    @Test
    void twoSum() {
        Solution_1 solution = new Solution_1();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        int[] result = solution.twoSum(nums, target);
        assertArrayEquals(expected, result);
    }
}
