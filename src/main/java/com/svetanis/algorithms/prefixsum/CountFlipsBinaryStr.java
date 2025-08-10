package com.svetanis.algorithms.prefixsum;

// 926. Flip String to Monotone Increasing

public final class CountFlipsBinaryStr {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int minFlipsSimple(String s) {
		int ones = 0;
		int zeros = 0;
		for (char c : s.toCharArray()) {
			int bit = c - '0'; // 0 or 1
			zeros = Math.min(zeros + 1 - bit, ones);
			ones += bit;
		}
		return Math.min(zeros, ones);
	}

	public static int minFlips(String s) {
		int n = s.length();
		int[] prefix = prefix(s);
		int[] suffix = suffix(s);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= n; i++) {
			int total = prefix[i] + suffix[i];
			min = Math.min(min, total);
		}
		return min;
	}

	private static int[] suffix(String s) {
		int n = s.length();
		int[] right = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			int count = s.charAt(i) == '1' ? 0 : 1;
			right[i] = right[i + 1] + count;
		}
		return right;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] left = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int count = s.charAt(i - 1) == '0' ? 0 : 1;
			left[i] = left[i - 1] + count;
		}
		return left;
	}

	public static void main(String[] args) {
		System.out.println(minFlips("00110")); // 1
		System.out.println(minFlips("010110")); // 2
		System.out.println(minFlips("00011000")); // 2

		System.out.println(minFlipsSimple("00110")); // 1
		System.out.println(minFlipsSimple("010110")); // 2
		System.out.println(minFlipsSimple("00011000")); // 2
	}
}
