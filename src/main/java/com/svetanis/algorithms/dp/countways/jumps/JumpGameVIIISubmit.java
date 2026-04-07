package com.svetanis.algorithms.dp.countways.jumps;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 2297. Jump Game VIII

public final class JumpGameVIIISubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public long minCost(int[] nums, int[] costs) {
		int n = nums.length;
		long[] dp = new long[n];
		Arrays.fill(dp, 1L << 60);
		dp[0] = 0;
		Deque<Integer> maxDq = new ArrayDeque<>();
		Deque<Integer> minDq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!maxDq.isEmpty() && nums[i] >= nums[maxDq.peek()]) {
				dp[i] = Math.min(dp[i], dp[maxDq.pop()] + costs[i]);
			}
			while (!minDq.isEmpty() && nums[i] < nums[minDq.peek()]) {
				dp[i] = Math.min(dp[i], dp[minDq.pop()] + costs[i]);
			}
			maxDq.push(i);
			minDq.push(i);
		}
		return dp[n - 1];
	}

	public static void main(String[] args) {
		JumpGameVIIISubmit jg = new JumpGameVIIISubmit();
		int[] nums1 = { 3, 2, 4, 4, 1 };
		int[] costs1 = { 3, 7, 6, 4, 2 };
		System.out.println(jg.minCost(nums1, costs1)); // 8

		int[] nums2 = { 0, 1, 2 };
		int[] costs2 = { 1, 1, 1 };
		System.out.println(jg.minCost(nums2, costs2)); // 2
	}
}
