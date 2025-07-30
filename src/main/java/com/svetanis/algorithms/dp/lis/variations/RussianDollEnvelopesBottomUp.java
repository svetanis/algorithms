package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;

// 354. Russian Doll Envelopes

public final class RussianDollEnvelopesBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int rde(int[][] envelopes) {
		int n = envelopes.length;
		if (n == 0) {
			return 0;
		}
		// sort dolls by width in ascending order
		// if widths are equal sort dolls by height
		Arrays.sort(envelopes, (a, b) -> a[0] == b[0] 
				? b[1] - a[1] : a[0] - b[0]);
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int maxEnvelopes = 0;
		for (int i = 0; i < n; i++) {
			int envelop = envelopes[i][1];
			for (int j = 0; j < i; j++) {
				if (envelop > envelopes[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxEnvelopes = Math.max(maxEnvelopes, dp[i]);
		}
		return maxEnvelopes;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
		System.out.println(rde(g1)); // 3: 2,3 -> 5,4 -> 6,7
		int[][] g2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		System.out.println(rde(g2)); // 1
	}
}