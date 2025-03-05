package leetcode.p_20__valid_parenthesis;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        int top = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                if (ch == ')' && stack.pop() != '(') return false;
                if (ch == ']' && stack.pop() != '[') return false;
                if (ch == '}' && stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}
