package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.reverseOrder;

import java.util.Arrays;
import java.util.PriorityQueue;

// 857. Minimum Cost to Hire K Workers
//
// Worker i has quality[i] and asks at least wage[i]. Hire exactly k workers; within the group each
// is paid in proportion to quality, and nobody gets less than his wage. Returns the least total
// pay (within 10^-5).
//
// Pay per unit of quality is one rate for the whole group, and it must cover the worker with the
// highest wage / quality ratio -- call that his efficiency. So fix who sets the rate: walk the
// workers by efficiency, lowest first; the one just reached has the highest rate so far, and the
// cheapest group under that rate is him plus the smallest qualities seen. A max-heap of qualities
// keeps the k smallest, and the largest quality is the one evicted. Cost = total quality * rate.
//
// Time: O(n log n + n log k) -- the sort, then a heap of at most k + 1 qualities
// Space: O(n) for the workers
//
// Siblings, same move (sort by the factor, heap the summed part):
//   MaxPerformanceTeam (1383), MaxSubSeqScore (2542) -- a maximum, so the factor is a minimum and
//     the heap evicts the smallest

public final class MinCost {

	public static double minCost(int[] quality, int[] wage, int k) {
		Worker[] workers = workers(quality, wage);
		double min = Double.MAX_VALUE;
		int total = 0;                                                // SUM: of the qualities in the heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder()); // MAX-HEAP: the largest kept quality on top
		for (Worker worker : workers) {
			pq.add(worker.quality);                                   // PUSH: he joins; his rate is the highest so far
			total += worker.quality;
			if (pq.size() > k) {
				total -= pq.poll();                                   // EVICT: the largest quality costs the most at any rate
			}
			if (pq.size() == k) {
				min = Math.min(min, total * worker.efficiency);       // RECORD: k workers, all paid at his rate
			}
		}
		return min;                                                   // REPORT
	}

	private static Worker[] workers(int[] quality, int[] wage) {
		int n = quality.length;
		Worker[] workers = new Worker[n];
		for (int i = 0; i < n; i++) {
			double efficiency = (double) wage[i] / quality[i];        // RATE: the pay per unit of quality he needs
			workers[i] = new Worker(quality[i], efficiency);
		}
		Arrays.sort(workers, (a, b) -> Double.compare(a.efficiency, b.efficiency)); // ORDER: lowest rate first
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
