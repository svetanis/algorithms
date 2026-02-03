package com.svetanis.algorithms.string;

// 1422. Maximum Score After Splitting a String

public final class MaxScoreAfterSplitting {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxScoreOnePass(String s) {
		int n = s.length();
		int ones = 0;
		int zeros = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == '1') {
				ones += 1;
			} else {
				zeros += 1;
			}
			max = Math.max(max, zeros - ones);
		}
		if (s.charAt(n - 1) == '1') {
			ones += 1;
		}
		return max + ones;
	}

	public static int maxScore(String s) {
		int n = s.length();
		int total = 0;
		if (s.charAt(0) == '0') {
			total++;
		}
		for (int i = 1; i < n; i++) {
			total += (s.charAt(i) == '1' ? 1 : 0);
		}
		int max = total;
		for (int i = 1; i < n - 1; i++) {
			total += (s.charAt(i) == '0' ? 1 : -1);
			max = Math.max(max, total);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxScore("011101")); // 5
		System.out.println(maxScore("00111")); // 5
		System.out.println(maxScore("1111")); // 3
	}
}
