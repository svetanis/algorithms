package com.svetanis.algorithms.twopointers;

// 32. Longest Valid Parentheses

public final class LongestValidParenthesesTwoPointers {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int lvp(String s) {
		int max = leftToRight(s);
		int left = 0;
		int right = 0;
		// right to left traversal
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}

			if (left == right) {
				max = Math.max(max, 2 * left);
			} else if (left > right) {
				left = right = 0;
			}
		}
		return max;
	}

	private static int leftToRight(String s) {
		int max = 0;
		int left = 0;
		int right = 0;
		// left to right traversal
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}

			if (left == right) {
				max = Math.max(max, 2 * right);
			} else if (right > left) {
				left = right = 0;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lvp("")); // 0
		System.out.println(lvp("(()")); // 2
		System.out.println(lvp(")()())")); // 4
		System.out.println(lvp("()(()))))"));// 6
	}
}