package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 2599. Make the Prefix Sum Non-negative

public final class MakePrefixSumNonNegative {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int prefSum(int[] a) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		long sum = 0;
		int count = 0;
		for (int num : a) {
			sum += num;
			if (num < 0) {
				pq.offer(num);
			}
			while (sum < 0) {
				sum -= pq.poll();
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 2, -3, -8, 4, -6, 1 };
		System.out.println(prefSum(a)); // 2
	}
}
