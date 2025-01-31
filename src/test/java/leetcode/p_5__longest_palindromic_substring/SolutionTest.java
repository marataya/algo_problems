package leetcode.p_5__longest_palindromic_substring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void longestPalindrome() {
        Solution solution = new Solution();
        assertEquals("bb", solution.longestPalindrome("cbbd"));
        assertEquals("bab", solution.longestPalindrome("babad"));
    }

}
