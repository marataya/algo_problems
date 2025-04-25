package leetcode.p_3__longest_substring_without_repeating_characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionBottomupTest {

    @Test
    void testLengthOfLongestSubstring() {
        Solution solution = new Solution();
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));

    }
}
