//package leetcode.p_3__longest_substring_without_repeating_characters;
//
//class Solution {
//    public int lengthOfLongestSubstring(String s) {
//        int n = s.length();
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j <= n; j++) {
//                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
//            }
//        }
//
//    }
//}
