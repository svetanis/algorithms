package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children

// the closed-form rung, and the one to submit. MaximizeHappinessSimulated.java
// runs the same greedy turn by turn; this collapses the k turns into
// a[n - 1 - i] - i, because the i-th pick has been decremented exactly i times.

public final class MaximizeHappiness {
	// Time Complexity: O(n log n + k)

	// note: sorts the caller's array in place. That reorders the input but
	// keeps every value, so calling this twice is safe -- unlike the
	// simulated sibling, which had to be given a defensive copy.

	public static long maximizeHappiness(int[] a, int k) {
		sort(a);
		int n = a.length;
		long total = 0;
		for (int i = 0; i < k; i++) {
			int curr = a[n - 1 - i] - i;
			total += max(curr, 0);
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
