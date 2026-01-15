package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1167. Minimum Cost to Connect Sticks

public final class MinCostConnectSticks {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int connectSticks(int[] a) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int num : a) {
			pq.offer(num);
		}
		int totalCost = 0;
		while (pq.size() > 1) {
			int first = pq.poll();
			int second = pq.poll();
			int cost = first + second;
			totalCost += cost;
			pq.offer(cost);
		}
		return totalCost;
	}

	public static void main(String[] args) {
		int[] a = { 1, 8, 3, 5 };
		System.out.println(connectSticks(a)); // 30
	}
}
