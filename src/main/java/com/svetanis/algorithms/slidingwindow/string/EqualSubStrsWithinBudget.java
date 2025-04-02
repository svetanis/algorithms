package com.svetanis.algorithms.slidingwindow.string;

// 1208. Get Equal Substrings Within Budget

public final class EqualSubStrsWithinBudget {
	// Time Complexity: O(n)

	public static int ess(String s, String target, int maxCost) {
		int cost = 0;
		int left = 0;
		int maxLen = 0;
		for (int right = 0; right < s.length(); right++) {
			cost += Math.abs(s.charAt(right) - target.charAt(right));
			while (cost > maxCost) {
				cost -= Math.abs(s.charAt(left) - target.charAt(left));
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);
		}
		return maxLen;
	}

	public static void main(String[] args) {
		System.out.println(ess("abcd", "bcdf", 3)); // 3
		System.out.println(ess("abcd", "cdef", 3)); // 1
		System.out.println(ess("abcd", "acde", 0)); // 1
	}
}
