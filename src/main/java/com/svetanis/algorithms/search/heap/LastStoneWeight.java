package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;
import java.util.Queue;

// 1046. Last Stone Weight

public final class LastStoneWeight {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int lsw(int[] stones) {
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int stone : stones) {
			pq.offer(stone);
		}
		while (pq.size() > 1) {
			int first = pq.poll();
			int second = pq.poll();
			if (first != second) {
				pq.offer(first - second);
			}
		}
		return pq.isEmpty() ? 0 : pq.poll();
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println(lsw(a1)); // 1
		int[] a2 = { 1 };
		System.out.println(lsw(a2)); // 1
	}
}
