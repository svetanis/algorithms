package com.svetanis.algorithms.greedy;

import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children
//
// Input: input[i] -- the happiness of child i; k -- how many children to pick, one per turn. After
// each pick, every child not yet picked loses 1 happiness, but never goes below 0. Returns the
// largest possible total happiness of the k picked children. On LeetCode: 1 <= n == input.length
// <= 2 * 10^5, 1 <= input[i] <= 10^8, 1 <= k <= n. The code reads a[n - 1 - count] for every turn,
// so it needs k <= n. The total can pass 2^31, so it is a long.
//
// The one idea: what a child loses depends only on WHEN it is picked, not on who it is, so the
// smallest loss should go on the largest happiness: on every turn, pick the happiest child not yet
// picked. This file acts the process out exactly as the problem tells it. Each turn takes the
// happiest remaining child, sets it to 0 so it is out of the game, and lowers every other child by
// 1, never below 0. Lowering everyone by the same amount keeps the sorted order, so the happiest
// remaining child is always the next one in from the end, a[n - 1 - count]. Nothing is tried and
// thrown away: it is the same greedy as MaximizeHappiness, with the turns acted out.
//
// Time: O(n log n + k * n) -- the sort, then each of the k turns walks the whole array to apply the
// loss.
// Space: O(n) -- the copy. The turns overwrite the values, so the method works on a copy and the
// caller's array comes back unchanged.
//
// Siblings: MaximizeHappiness gets the same answer in O(n log n + k): the child picked on turn i has
// lost exactly i, so it subtracts i instead of walking the array on every turn.

public final class MaximizeHappinessSimulated {

	public static long maximizeHappiness(int[] input, int k) {
		int[] a = input.clone(); // COPY: the turns below overwrite the values
		sort(a); // SORT: happiest at the end
		int n = a.length;
		int count = 0;
		long total = 0;
		while (count < k) {
			int index = n - 1 - count; // TAKE the happiest child not yet picked
			total += a[index];
			count++;
			for (int i = n - 1; i >= 0; i--) { // LOWER every child, once per turn
				if (i == index) {
					a[i] = 0; // OUT: the picked child counts for nothing from now on
				} else {
					a[i] = Math.max(a[i] - 1, 0); // FLOOR: never below 0
				}
			}
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

		// the same array again, then the sibling on it: the copy leaves a1 as it was
		System.out.println(maximizeHappiness(a1, 2)); // 4
		System.out.println(MaximizeHappiness.maximizeHappiness(a1, 2)); // 4
	}
}
