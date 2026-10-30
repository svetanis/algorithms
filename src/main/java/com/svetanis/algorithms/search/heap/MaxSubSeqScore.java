package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 2542. Maximum Subsequence Score
//
// Two arrays a and b of length n, and k. Choose exactly k indices; their score is (sum of a at
// those indices) * (smallest b at those indices). Returns the largest score.
//
// Fix which index holds the smallest b. Walk the pairs by b, largest first: the pair just reached
// has the smallest b of everything seen, so the best choice with it as the minimum is it plus the
// k - 1 largest a values seen before it. A min-heap of a values keeps the k largest; once it holds
// k, record sum * b and evict the smallest a, leaving k - 1 for the next pair.
//
// Time: O(n log n) -- the sort, then a heap of at most k values
// Space: O(n) for the pairs, O(k) for the heap
//
// Siblings, same move (sort by the factor that is a minimum, heap the summed part):
//   MaxPerformanceTeam (1383) -- at most k chosen, and the answer is taken modulo 10^9 + 7
//   MinCost (857)             -- the minimum cost, so the factor is a maximum and the heap evicts the largest
//   MaxSubSeqScoreIndices     -- this problem again: sorts indices instead of pairs, and scores before the push

public final class MaxSubSeqScore {

	public static long maxScore(int[] a, int[] b, int k) {
		long max = 0;
		long sum = 0;                              // SUM: of the a values in the heap, a long
		List<Pair> pairs = pairs(a, b);
		Queue<Integer> pq = new PriorityQueue<>(); // MIN-HEAP: the smallest kept a value on top
		for (Pair p : pairs) {
			sum += p.first;                        // ADD: this pair joins; p.second is the smallest b so far
			pq.offer(p.first);                     // PUSH: its a value
			if (pq.size() == k) {
				max = Math.max(max, sum * p.second); // RECORD: exactly k chosen, and this pair is among them
				sum -= pq.poll();                  // EVICT: the smallest a, leaving k - 1 for the next pair
			}
		}
		return max;                                // REPORT
	}

	private static List<Pair> pairs(int[] a, int[] b) {
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			list.add(new Pair(a[i], b[i]));        // PAIR: a[i] with its b[i]
		}
		Collections.sort(list, comparingInt((Pair p) -> p.second).reversed()); // ORDER: largest b first
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