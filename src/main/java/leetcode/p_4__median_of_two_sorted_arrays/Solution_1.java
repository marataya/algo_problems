package leetcode.p_4__median_of_two_sorted_arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        for (; i < m; i++) merged[k++] = nums1[i++];
        for (; j < n; j++) merged[k++] = nums2[j++];

        if (merged.length % 2 == 0) {
            return (merged[merged.length / 2] + merged[merged.length / 2 - 1]) / 2.0;
        } else {
            return merged[merged.length / 2];
        }
    }
}
