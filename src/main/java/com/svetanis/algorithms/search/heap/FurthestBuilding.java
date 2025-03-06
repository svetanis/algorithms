package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 1642. Furthest Building You Can Reach

public final class FurthestBuilding {
	// Time Complexity: O(n log l)

	public static int furthestBuilding(int[] heights, int bricks, int ladders) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < heights.length - 1; i++) {
			int diff = heights[i + 1] - heights[i];
			if (diff > 0) {
				pq.offer(diff);
				if (pq.size() > ladders) {
					bricks -= pq.poll();
				}
				if (bricks < 0) {
					return i;
				}
			}
		}
		return heights.length - 1;
	}

	public static void main(String[] args) {
		int[] heights = { 4, 2, 7, 6, 9, 14, 12 };
		System.out.println(furthestBuilding(heights, 5, 1)); // 4
		int[] heights2 = { 4, 12, 2, 7, 3, 18, 20, 3, 19 };
		System.out.println(furthestBuilding(heights2, 10, 2)); // 7
		int[] heights3 = { 14, 3, 19, 3 };
		System.out.println(furthestBuilding(heights3, 17, 0)); // 3
	}
}