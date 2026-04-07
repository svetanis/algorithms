package com.svetanis.algorithms.dp.countways.jumps;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// 2297. Jump Game VIII

public final class JumpGameVIII {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private List<Integer>[] graph;

	public long minCost(int[] nums, int[] costs) {
		int n = nums.length;
		this.graph = new List[n];
		Arrays.setAll(graph, index -> new ArrayList<>());
		nextGreater(nums);
		nextSmaller(nums);
		long[] dp = new long[n];
		Arrays.fill(dp, 1L << 60);
		dp[0] = 0;
		for (int src = 0; src < n; src++) {
			for (int dst : graph[src]) {
				long jump = dp[src] + costs[dst];
				dp[dst] = Math.min(dp[dst], jump);
			}
		}
		return dp[n - 1];
	}

	private void nextGreater(int[] a) {
		Deque<Integer> dq = new ArrayDeque<>();
		int n = a.length;
		for (int i = n - 1; i >= 0; i--) {
			while (!dq.isEmpty() && a[dq.peek()] < a[i]) {
				dq.pop();
			}
			if (!dq.isEmpty()) {
				graph[i].add(dq.peek());
			}
			dq.push(i);
		}
	}

	private void nextSmaller(int[] a) {
		Deque<Integer> dq = new ArrayDeque<>();
		int n = a.length;
		for (int i = n - 1; i >= 0; i--) {
			while (!dq.isEmpty() && a[dq.peek()] >= a[i]) {
				dq.pop();
			}
			if (!dq.isEmpty()) {
				graph[i].add(dq.peek());
			}
			dq.push(i);
		}
	}

	public static void main(String[] args) {
		JumpGameVIII jg = new JumpGameVIII();
		int[] nums1 = { 3, 2, 4, 4, 1 };
		int[] costs1 = { 3, 7, 6, 4, 2 };
		System.out.println(jg.minCost(nums1, costs1)); // 8

		int[] nums2 = { 0, 1, 2 };
		int[] costs2 = { 1, 1, 1 };
		System.out.println(jg.minCost(nums2, costs2)); // 2
	}
}
