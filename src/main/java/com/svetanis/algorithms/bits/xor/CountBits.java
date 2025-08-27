package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// 338. Counting Bits

public final class CountBits {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] count(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i / 2] + i % 2;
		}
		return dp;
	}

	public static int[] count1(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int index = i & (i - 1);
			dp[i] = dp[index] + 1;
		}
		return dp;
	}

	public static int[] count2(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int index = i >> 1;
			dp[i] = dp[index] + (i & 1);
		}
		return dp;
	}

	public static void main(String[] args) {
		print(count(2)); // [0,1,1]
		print(count(5)); // [0,1,1,2,1,2]
	}
}