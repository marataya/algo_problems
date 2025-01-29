package leetcode.p_4__median_of_two_sorted_arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void testFindMedianSortedArrays_OddLength() {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.0, result, "Median of [1, 3] and [2] should be 2.0");
    }

    @Test
    void testFindMedianSortedArrays_EvenLength() {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.5, result, "Median of [1, 2] and [3, 4] should be 2.5");
    }

    @Test
    void testFindMedianSortedArrays_EmptyFirstArray() {
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.0, result, "Median of [] and [1, 2, 3] should be 2.0");
    }

    @Test
    void testFindMedianSortedArrays_EmptySecondArray() {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.0, result, "Median of [1, 2, 3] and [] should be 2.0");
    }

    @Test
    void testFindMedianSortedArrays_BothArraysEmpty() {
        int[] nums1 = {};
        int[] nums2 = {};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            solution.findMedianSortedArrays(nums1, nums2);
        }, "Empty arrays should throw an exception");
    }

    @Test
    void testFindMedianSortedArrays_SingleElementArrays() {
        int[] nums1 = {5};
        int[] nums2 = {10};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(7.5, result, "Median of [5] and [10] should be 7.5");
    }

    @Test
    void testFindMedianSortedArrays_LargeArrays() {
        int[] nums1 = {1, 3, 5, 7, 9};
        int[] nums2 = {2, 4, 6, 8, 10};
        double result = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(5.5, result, "Median of [1, 3, 5, 7, 9] and [2, 4, 6, 8, 10] should be 5.5");
    }
}
