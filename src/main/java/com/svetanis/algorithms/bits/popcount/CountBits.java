package com.svetanis.algorithms.bits.popcount;

import java.util.Arrays;

// 338. Counting Bits
//
// Input: an int n. Returns an array ans of length n + 1 where ans[i] is the number of 1s in the
// binary form of i. On LeetCode: 0 <= n <= 10^5.
//
// The one idea: dp[i] holds the number of 1s in i. Each i has a smaller number already counted
// that differs from it in a known way. i without its slot 0 is i / 2, the same as i >> 1, so
// dp[i] = dp[i / 2] + (slot 0 of i). Or remove i's lowest 1: i & (i - 1) is smaller and has
// exactly one 1 fewer, so dp[i] = dp[i & (i - 1)] + 1. Both smaller numbers come before i, so a
// left-to-right fill always finds them ready.
//
// Time: O(n) -- one constant-time cell per number.
// Space: O(n) -- the answer array, which is the output.
//
// Siblings: count and count2 are the same rule (i / 2 and i % 2, or i >> 1 and i & 1); count1
// removes the lowest 1 instead. HammingWeight191 counts the 1s of one number.

public final class CountBits {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] count(int n) {
		int[] dp = new int[n + 1]; // CELL: dp[i] -- the number of 1s in i
		dp[0] = 0; // BASE: 0 has no 1s
		for (int i = 1; i <= n; i++) { // FILL: left to right, every smaller number is ready
			dp[i] = dp[i / 2] + i % 2; // DROP slot 0: i / 2 is i without slot 0; i % 2 is slot 0
		}
		return dp;
	}

	public static int[] count1(int n) {
		int[] dp = new int[n + 1]; // CELL: dp[i] -- the number of 1s in i
		dp[0] = 0; // BASE: 0 has no 1s
		for (int i = 1; i <= n; i++) { // FILL: left to right
			int index = i & (i - 1); // DROP the lowest 1: smaller, one 1 fewer
			dp[i] = dp[index] + 1; // ADD the 1 that was dropped
		}
		return dp;
	}

	public static int[] count2(int n) {
		int[] dp = new int[n + 1]; // CELL: dp[i] -- the number of 1s in i
		dp[0] = 0; // BASE: 0 has no 1s
		for (int i = 1; i <= n; i++) { // FILL: left to right
			int index = i >> 1; // DROP slot 0
			dp[i] = dp[index] + (i & 1); // ADD slot 0 back: 0 or 1
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(count(2))); // [0, 1, 1]
		System.out.println(Arrays.toString(count(5))); // [0, 1, 1, 2, 1, 2]
		System.out.println(Arrays.toString(count1(5))); // [0, 1, 1, 2, 1, 2]
		System.out.println(Arrays.toString(count2(5))); // [0, 1, 1, 2, 1, 2]
	}
}