package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.Arrays;

// 698. Partition to K Equal Sum Subsets

public final class PartitionKEqualSumSubSets {
	// Time Complexity: O(k^n)
	// Space Complexity: O(k + n)

	public static boolean canPartition(int[] a, int k) {
		int sum = Arrays.stream(a).sum();
		if (sum % k != 0) {
			return false;
		}
		sortInPlace(a);
		int[] dp = new int[k];
		return dfs(a.length - 1, a, dp, sum / k);
	}

	private static void sortInPlace(int[] a) {
		int n = a.length;
		Arrays.sort(a);
		// reverse
		for (int left = 0, right = n - 1; left < n; left++, right--) {
			int temp = a[left];
			a[left] = a[right];
			a[right] = temp;
		}
	}

	private static boolean dfs(int index, int[] a, int[] dp, int target) {
		if (index < 0) {
			return isSum(dp, target);
		}
		for (int i = 0; i < dp.length; i++) {

			// skip duplicates
			if (i > 0 && dp[i] == dp[i - 1]) {
				continue;
			}
			dp[i] += a[index];
			if (dp[i] <= target && dfs(index - 1, a, dp, target)) {
				return true;
			}
			// backtrack
			dp[i] -= a[index];

			if (dp[i] == 0) {
				break;
			}
		}
		return false;
	}

	private static boolean isSum(int[] dp, int target) {
		for (int sum : dp) {
			if (sum != target) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 3, 2, 3, 5, 2, 1 };
		System.out.println(canPartition(a1, 4)); // true
		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(canPartition(a2, 3)); // false
		int[] a3 = { 2, 2, 2, 2, 3, 4, 5 };
		System.out.println(canPartition(a3, 4)); // false
	}
}
