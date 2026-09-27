package com.svetanis.algorithms.bits.popcount;

import static com.svetanis.java.base.utils.Print.print;

// 338. Counting Bits

// a table whose smaller case is a number already counted:
// i >> 1 (i without its slot 0), or i & (i - 1) (i without its lowest 1)

public final class CountBits {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] count(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i / 2] + i % 2; // i / 2 is i without slot 0; i % 2 is slot 0
		}
		return dp;
	}

	public static int[] count1(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int index = i & (i - 1); // i without its lowest 1: smaller, one 1 fewer
			dp[i] = dp[index] + 1;
		}
		return dp;
	}

	public static int[] count2(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int index = i >> 1; // i without slot 0
			dp[i] = dp[index] + (i & 1);
		}
		return dp;
	}

	public static void main(String[] args) {
		print(count(2)); // [0,1,1]
		print(count(5)); // [0,1,1,2,1,2]
	}
}