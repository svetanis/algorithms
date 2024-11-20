package com.svetanis.algorithms.dp.dns;

// 813. Largest Sum of Averages

public final class LargestSumOfAverages {
	// Time Complexity: O(n^2 * k)
	// Space Complexity: O(n * k)

	public static double maxSum(int[] a, int k) {
		int n = a.length;
		int[] prefix = prefix(a);
		Double[][] dp = new Double[n + 1][k + 1];
		return dfs(0, k, prefix, dp);
	}

	private static double dfs(int index, int k, int[] prefix, Double[][] dp) {
		int n = prefix.length - 1; // size of input array
		if (index == n) {
			return 0;
		}
		if (k == 1) {
			int sum = prefix[n] - prefix[index];
			return (double) sum / (n - index);
		}
		if (dp[index][k] != null) {
			return dp[index][k];
		}
		double max = 0;
		for (int i = index; i < n; i++) {
			int size = i - index + 1;
			int sum = prefix[i + 1] - prefix[index];
			double avg = (double) sum / size;
			max = Math.max(max, avg + dfs(i + 1, k - 1, prefix, dp));
		}
		return dp[index][k] = max;
	}

	private static int[] prefix(int[] a) {
		int n = a.length;
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + a[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 1, 2, 3, 9 };
		int[] a2 = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(maxSum(a1, 3)); // 20
		System.out.println(maxSum(a2, 4)); // 20.5
	}
}
