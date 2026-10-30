package com.svetanis.algorithms.search.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 2542. Maximum Subsequence Score
//
// Input: two arrays nums1 and nums2 of length n, and k. Choose exactly k indices; their score is
// (sum of nums1 at those indices) * (smallest nums2 at those indices). Returns the largest score.
// On LeetCode: 1 <= n <= 10^5, 0 <= nums1[i], nums2[i] <= 10^5, 1 <= k <= n.
//
// The one idea: let every row take a turn as the group's minimum nums2. Once that row is chosen,
// the minimum cannot move, so the best group is that row plus the k - 1 largest nums1 among the
// rows allowed to join it -- the rows whose nums2 is at least its own. Walk the indices sorted by
// nums2, largest first, and the rows allowed to join are exactly the rows already passed. A
// min-heap keeps the k - 1 largest nums1 passed: when a new one arrives and there are too many,
// the smallest is the one to throw out. A running sum beside the heap saves re-adding it.
//
// This version sorts an array of indices instead of building pairs, and scores a row BEFORE it
// joins the heap: the heap holds k - 1 partners, and the row itself is added only in the score.
// MaxSubSeqScore, beside it, pushes first and scores once the heap holds k. The two agree on every
// input. Siblings with the same move: MaxPerformanceTeam (1383) and MinCost (857).
//
// Time: O(n log n) -- the sort, then n heap operations on at most k values.
// Space: O(n) -- the index array; the heap holds at most k values.
//
// keptSum and the score are long: the largest input gives 10^5 * 10^5 * 10^5 = 10^15.

public final class MaxSubSeqScoreIndices {

	public static long maxScore(int[] nums1, int[] nums2, int k) {
		int n = nums1.length;
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++) {
			order[i] = i;
		}
		Arrays.sort(order, (a, b) -> Integer.compare(nums2[b], nums2[a])); // SORT: largest nums2 first
		PriorityQueue<Integer> kept = new PriorityQueue<>(); // KEEP: the k - 1 largest nums1 passed
		long keptSum = 0; // and their sum
		long best = 0;
		for (int i : order) { // row i is the minimum of its group
			if (kept.size() == k - 1) { // SCORE: read only -- keptSum stays the heap's sum
				best = Math.max(best, (keptSum + nums1[i]) * nums2[i]);
			}
			kept.offer(nums1[i]); // PASS: a partner for the rows still to come
			keptSum += nums1[i];
			if (kept.size() > k - 1) {
				int smallest = kept.poll(); // THROW OUT the smallest
				keptSum -= smallest;
			}
		}
		return best; // REPORT
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 3, 2 };
		int[] a2 = { 2, 1, 3, 4 };
		System.out.println(maxScore(a1, a2, 3)); // 12

		int[] a3 = { 4, 2, 3, 1, 1 };
		int[] a4 = { 7, 5, 10, 9, 6 };
		System.out.println(maxScore(a3, a4, 1)); // 30

		int[] a5 = { 1, 4, 7, 9, 2 };
		int[] a6 = { 9, 8, 7, 6, 5 };
		System.out.println(maxScore(a5, a6, 3)); // 120 -- the 9 with partners 4 and 7, minimum 6
	}
}
