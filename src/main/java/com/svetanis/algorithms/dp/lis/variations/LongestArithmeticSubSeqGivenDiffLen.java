package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1218. Longest Arithmetic Subsequence of Given Difference

public final class LongestArithmeticSubSeqGivenDiffLen {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int lis(int[] a, int target) {
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int prev = a[i] - target;
			if (map.containsKey(prev)) {
				int index = map.get(prev);
				dp[i] = dp[index] + 1;
			}
			map.put(a[i], i);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(lis(a1, 1)); // 4
		int[] a2 = { 1, 3, 5, 7 };
		System.out.println(lis(a2, 1)); // 1
	}
}