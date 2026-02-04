package com.svetanis.algorithms.dp.dns;

// 1043. Partition Array for Maximum Sum

public final class PartitionArrayForMaxSumTopDown {
	// Time Complexity: O(n * k)
	// Space Complexity: O(n)

	private int n;
	private int k;
	private int[] a;

	public int maxSum(int[] a, int k) {
		this.k = k;
		this.a = a;
		this.n = a.length;
		Integer[] dp = new Integer[n];
		return dfs(0, dp);
	}

	private int dfs(int index, Integer[] dp) {
		if (index >= n) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int currMax = 0, max = 0;
		int end = Math.min(n, index + k);
		for (int i = index; i < end; i++) {
			currMax = Math.max(currMax, a[i]);
			int sum = currMax * (i - index + 1);
			max = Math.max(max, sum + dfs(i + 1, dp));
		}
		return dp[index] = max;
	}

	public static void main(String[] args) {
		PartitionArrayForMaxSumTopDown msa = new PartitionArrayForMaxSumTopDown();
		int[] a1 = { 1, 15, 7, 9, 2, 5, 10 };
		int[] a2 = { 1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3 };
		int[] a3 = { 1 };
		System.out.println(msa.maxSum(a1, 3)); // 84
		System.out.println(msa.maxSum(a2, 4)); // 83
		System.out.println(msa.maxSum(a3, 1)); // 1
	}
}
