package com.svetanis.algorithms.search.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 2542. Maximum Subsequence Score

public final class MaxSubSeqScore {
	// Time Complexity: O(n log(n*k))
	// Space Complexity: O(n + k)

	public static long maxScore(int[] a, int[] b, int k) {
		long max = 0;
		long sum = 0;
		List<Pair> pairs = pairs(a, b);
		Queue<Integer> pq = new PriorityQueue<>();
		for (Pair p : pairs) {
			sum += p.first;
			pq.offer(p.first);
			if (pq.size() == k) {
				max = Math.max(max, sum * p.second);
				sum -= pq.poll();
			}
		}
		return max;
	}

	private static List<Pair> pairs(int[] a, int[] b) {
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			list.add(new Pair(a[i], b[i]));
		}
		// sort pairs based on second element in decreasing order
		Collections.sort(list, (p1, p2) -> p2.second - p1.second);
		return list;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 3, 2 };
		int[] a2 = { 2, 1, 3, 4 };
		System.out.println(maxScore(a1, a2, 3)); // 12

		int[] a3 = { 4, 2, 3, 1, 1 };
		int[] a4 = { 7, 5, 10, 9, 6 };
		System.out.println(maxScore(a3, a4, 1)); // 30
	}

	private static class Pair {
		private int first;
		private int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}