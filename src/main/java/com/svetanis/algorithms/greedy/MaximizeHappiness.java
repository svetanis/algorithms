package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children
//
// Input: a[i] -- the happiness of child i; k -- how many children to pick, one per turn. After each
// pick, every child not yet picked loses 1 happiness, but never goes below 0. Returns the largest
// possible total happiness of the k picked children. On LeetCode: 1 <= n == a.length <= 2 * 10^5,
// 1 <= a[i] <= 10^8, 1 <= k <= n. The code reads a[n - 1 - i] for every i < k, so it needs k <= n.
// The total can pass 2^31, so it is a long.
//
// The one idea: what a child loses depends only on WHEN it is picked, not on who it is. The child
// picked on turn i (counting from 0) has sat through i earlier picks and lost 1 on each, so it
// brings in its happiness minus i, or 0 if that is negative. The losses 0, 1, 2, ... are the same
// whoever is picked, so the best use of them is to put the smallest loss on the largest happiness:
// pick the happiest child first, then the next happiest, and so on. Picking a less happy child, or
// the same children in another order, can only lower the total, so no pick ever needs to be undone.
// Sorting puts the happiest child at the end, so turn i takes a[n - 1 - i] and subtracts i, and
// max(curr, 0) is the floor at 0.
//
// Time: O(n log n + k) -- the sort, then k turns of constant work.
// Space: O(log n) -- the sort works inside the caller's array; it reorders the array but keeps every
// value, so calling the method again on the same array gives the same answer.
//
// Siblings: MaximizeHappinessSimulated makes the same picks but acts out every turn, lowering each
// remaining child by 1, which costs O(n log n + k * n); this file replaces that inner loop with the
// subtraction of i.

public final class MaximizeHappiness {

	public static long maximizeHappiness(int[] a, int k) {
		sort(a); // SORT: happiest at the end
		int n = a.length;
		long total = 0;
		for (int i = 0; i < k; i++) {
			int curr = a[n - 1 - i] - i; // TAKE: the i-th pick has been decremented i times
			total += max(curr, 0); // FLOOR: happiness never goes below 0
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = { 1, 1, 1, 1 };
		int[] a3 = { 2, 3, 4, 5 };
		System.out.println(maximizeHappiness(a1, 2)); // 4
		System.out.println(maximizeHappiness(a2, 2)); // 1
		System.out.println(maximizeHappiness(a3, 1)); // 5
	}
}
