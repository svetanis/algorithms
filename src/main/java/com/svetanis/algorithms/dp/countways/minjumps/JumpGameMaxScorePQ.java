package com.svetanis.algorithms.dp.countways.minjumps;

import java.util.PriorityQueue;

// 1696. Jump Game VI

public final class JumpGameMaxScorePQ {
	// Time Complexity: O(n log k)

	public static int maxScore(int[] a, int k) {
		int max = a[0];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
		pq.offer(new int[] { a[0], 0 });
		for (int i = 1; i < a.length; i++) {
			while (!pq.isEmpty() && i - pq.peek()[1] > k) {
				pq.poll();
			}
			max = pq.peek()[0] + a[i];
			pq.offer(new int[] { max, i });
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 1, -1, -2, 4, -7, 3 };
		System.out.println(maxScore(a, 2)); // 7

		int[] a1 = { 10, -5, -2, 4, 0, 3 };
		System.out.println(maxScore(a1, 3)); // 17

		int[] a2 = { 1, -5, -20, 4, -1, 3, -6, -3 };
		System.out.println(maxScore(a2, 2)); // 0

		int[] a3 = { -123 };
		System.out.println(maxScore(a3, 10)); // -123

	}
}
