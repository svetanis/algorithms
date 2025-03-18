package com.svetanis.algorithms.greedy;

import java.util.PriorityQueue;

// 871. Minimum Number of Refueling Stops

public final class MinNumOfRefuelingStops {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int mrs(int target, int startFuel, int[][] stations) {
		int n = stations.length;
		int count = 0;
		int index = 0;
		int fuel = startFuel;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		while (fuel < target) {
			while (index < n && stations[index][0] <= fuel) {
				pq.offer(stations[index++][1]);
			}
			if (pq.isEmpty()) {
				return -1;
			}
			fuel += pq.poll();
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] s1 = {};
		System.out.println(mrs(1, 1, s1)); // 0
		int[][] s2 = { { 10, 100 } };
		System.out.println(mrs(100, 1, s2)); // -1
		int[][] s3 = { { 10, 60 }, { 20, 30 }, { 30, 30 }, { 60, 40 } };
		System.out.println(mrs(100, 10, s3)); // 2
	}
}
