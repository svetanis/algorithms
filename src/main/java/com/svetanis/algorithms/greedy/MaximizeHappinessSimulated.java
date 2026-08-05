package com.svetanis.algorithms.greedy;

import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children

// the simulated rung: run the process the problem describes, one turn at a
// time -- take the largest remaining child, then decrement every other child
// by 1 (floored at 0), k times over.

// was named MaximizeHappinessBruteForce, which it is not: nothing is
// enumerated and no alternative is ever explored. Same greedy as
// MaximizeHappiness.java, just not folded into a formula.

public final class MaximizeHappinessSimulated {
	// Time Complexity: O(n log n + k * n)
	// the k * n is the whole point of keeping this file: each turn walks the
	// array to apply the decrement. MaximizeHappiness.java gets the same
	// answer in O(k) by noticing the i-th pick has been decremented i times.

	public static long maximizeHappiness(int[] input, int k) {
		// the simulation writes its working state into the array, so without
		// this copy the caller's data comes back zeroed
		int[] a = input.clone();
		sort(a);
		int n = a.length;
		int count = 0;
		long total = 0;
		while (count < k) {
			int index = n - 1 - count;
			total += a[index];
			count++;
			for (int i = n - 1; i >= 0; i--) {
				if (i == index) {
					a[i] = 0;
				} else {
					a[i] = Math.max(a[i] - 1, 0);
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

		// the same array twice, then handed to the sibling: all three agree
		// only because the input survives the call
		System.out.println(maximizeHappiness(a1, 2)); // 4
		System.out.println(MaximizeHappiness.maximizeHappiness(a1, 2)); // 4
	}
}
