package com.svetanis.algorithms.search.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 857. Minimum Cost to Hire K Workers

public final class MinCost {
	// Time Complexity: O(n log n + n log k)

	public static double minCost(int[] quality, int[] wage, int k) {
		Worker[] workers = workers(quality, wage);
		double min = Double.MAX_VALUE;
		int total = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (Worker worker : workers) {
			pq.add(worker.quality);
			total += worker.quality;
			if (pq.size() > k) {
				total -= pq.poll();
			}
			if (pq.size() == k) {
				min = Math.min(min, total * worker.efficiency);
			}
		}
		return min;
	}

	private static Worker[] workers(int[] quality, int[] wage) {
		int n = quality.length;
		Worker[] workers = new Worker[n];
		for (int i = 0; i < n; i++) {
			double efficiency = (double) wage[i] / quality[i];
			workers[i] = new Worker(quality[i], efficiency);
		}
		Arrays.sort(workers, (a, b) -> Double.compare(a.efficiency, b.efficiency));
		return workers;
	}

	public static void main(String[] args) {
		int[] q1 = { 10, 20, 5 };
		int[] w1 = { 70, 50, 30 };
		System.out.println(minCost(q1, w1, 2)); // 105.0

		int[] q2 = { 3, 1, 10, 10, 1 };
		int[] w2 = { 4, 8, 2, 2, 7 };
		System.out.println(minCost(q2, w2, 3)); // 30.66667
	}

	private static class Worker {
		private int quality;
		private double efficiency;

		public Worker(int quality, double efficiency) {
			this.quality = quality;
			this.efficiency = efficiency;
		}
	}
}
