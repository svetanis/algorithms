package com.svetanis.algorithms.dp.countways.jumps;

import java.util.ArrayDeque;
import java.util.Deque;

// 1696. Jump Game VI

public final class JumpGameMaxScore {
	// Time Complexity: O(n)

	public static int maxScore(int[] a, int k) {
		int n = a.length;
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(0);
		int[] dp = new int[n];
		dp[0] = a[0];
		for (int i = 1; i < n; i++) {
			while (!dq.isEmpty() && i - dq.peekFirst() > k) {
				dq.pollFirst();
			}
			dp[i] = dp[dq.peekFirst()] + a[i];
			while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
				dq.pollLast();
			}
			dq.offer(i);
		}
		return dp[n - 1];
	}

	public static void main(String[] args) {
		int[] a = { 1, -1, -2, 4, -7, 3 };
		System.out.println(maxScore(a, 2)); // 7

		int[] a1 = { 10, -5, -2, 4, 0, 3 };
		System.out.println(maxScore(a1, 3)); // 17

		int[] a2 = { 1, -5, -20, 4, -1, 3, -6, -3 };
		System.out.println(maxScore(a2, 2)); // 0
	}
}
